package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.excel_io.exceptions.*;
import br.edu.opi.manager.excel_io.models.CompetitorTableMetadata;
import br.edu.opi.manager.excel_io.models.CompetitorTableRow;
import br.edu.opi.manager.excel_io.repositories.CompetitorTableMetadataRepository;
import br.edu.opi.manager.excel_io.repositories.StudentTableRowRepository;
import br.edu.opi.manager.person.models.Genre;
import br.edu.opi.manager.school.models.Grade;
import br.edu.opi.manager.school.models.School;
import br.edu.opi.manager.school.services.SchoolService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CompetitorParserService {

	public static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	private static final int CELLS_LENGTH = 5;

	private SchoolService schoolService;

	private CompetitorTableMetadataRepository competitorTableMetadataRepository;

	private StudentTableRowRepository competitorTableRowRepository;

	private ServletContext context;

	@Autowired
	public CompetitorParserService(
			SchoolService schoolService,
			CompetitorTableMetadataRepository competitorTableMetadataRepository,
			StudentTableRowRepository competitorTableRowRepository,
			ServletContext context) {
		this.schoolService = schoolService;
		this.competitorTableMetadataRepository = competitorTableMetadataRepository;
		this.competitorTableRowRepository = competitorTableRowRepository;
		this.context = context;
	}

	public void createCompetitors(String delegatePrincipal, int year, MultipartFile multipartFile) {
		try {
			School school = schoolService.show(delegatePrincipal);
			createCompetitorsTransactional(school.getId(), year, multipartFile.getInputStream());
		} catch (IOException e) {
			throw new InvalidFileRuntimeException();
		}
	}

	@Transactional
	void createCompetitorsTransactional(Long schoolId, int year, InputStream excelFileInputStream) {
		CompetitorTableMetadata competitorTableMetadata = new CompetitorTableMetadata(year, new School(schoolId));
		CompetitorTableMetadata savedCompetitorTableMetadata = competitorTableMetadataRepository.save(competitorTableMetadata);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
			XSSFSheet worksheet = workbook.getSheetAt(0); // TODO: verify number of sheets with client
			for (int i = 1; i <= worksheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = worksheet.getRow(i);
				CompetitorTableRow tempStudent = new CompetitorTableRow();
				if (isCellsWithContent(row)) {
					tempStudent.setName(getNameAttribute(row.getCell(0)));
					tempStudent.setDateBirth(getDateAttribute(row.getCell(1)));
					tempStudent.setGenre(getGenreAttribute(row.getCell(2)));
					tempStudent.setGrade(getGradeAttribute(row.getCell(3)));
					tempStudent.setScore(getScoreAttibute(row.getCell(4)));
					tempStudent.setCompetitorTableMetadata(savedCompetitorTableMetadata);
					savedCompetitorTableMetadata.addRow(tempStudent);
				}
			}
			competitorTableMetadataRepository.save(savedCompetitorTableMetadata);
			new ConsolidateChangesInCompetitors(schoolId, savedCompetitorTableMetadata.getRows()).start();
		} catch (IOException e) {
			throw new InvalidFileRuntimeException();
		}
	}

	private static String getNameAttribute(XSSFCell cell) {
		try {
			String string = cell.getStringCellValue();
			if (string.isEmpty()) {
				throw new NameNotNullRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1);
			}
			return string;
		} catch (NameNotNullRuntimeException nnnre) {
			throw nnnre;
		} catch (Exception e) {
			throw new InvalidNameRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1, cell.toString());
		}
	}

	private static LocalDate getDateAttribute(XSSFCell cell) {
		try {
			Date date = cell.getDateCellValue();
			if (date == null) {
				throw new DateNotNullRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1);
			}
			return date.toInstant()
					.atZone(ZoneId.of("Brazil/East")) // UTC-03:00 @see https://www.timetemperature.com/samerica/brazil_time_zone.shtml
					.toLocalDate();
		} catch (DateNotNullRuntimeException dnnre) {
			throw dnnre;
		} catch (Exception e) {
			throw new InvalidDateRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1, cell.toString());
		}
	}

	private static Genre getGenreAttribute(XSSFCell cell) {
		try {
			String string = cell.getStringCellValue();
			if (string.isEmpty()) {
				throw new GenreNotNullRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1);
			}
			return Genre.from(string);
		} catch (GenreNotNullRuntimeException gnnre) {
			throw gnnre;
		} catch (Exception e) {
			throw new InvalidGenreRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1, cell.toString());
		}
	}

	private static Grade getGradeAttribute(XSSFCell cell) {
		try {
			String string = cell.getStringCellValue();
			if (string.isEmpty()) {
				throw new GradeNotNullRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1);
			}
			return Grade.from(string);
		} catch (GradeNotNullRuntimeException nnnre) {
			throw nnnre;
		} catch (Exception e) {
			throw new InvalidGradeRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1, cell.toString());
		}
	}

	private static Double getScoreAttibute(XSSFCell cell) {
		try {
			if (cell.toString().isEmpty()) {
				throw new ScoreNotNullRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1);
			}
			if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals("FALTOU")) {
				return CompetitorTableRow.MISSED_STUDENT;
			} else if (cell.getCellType() == CellType.NUMERIC) {
				return cell.getNumericCellValue();
			} else {
				throw new RuntimeException();
			}
		} catch (ScoreNotNullRuntimeException nnnre) {
			throw nnnre;
		} catch (Exception e) {
			throw new InvalidScoreRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1, cell.toString());
		}
	}

	private static boolean isCellsWithContent(XSSFRow row) {
		if (row == null || row.getLastCellNum() < CELLS_LENGTH) {
			return false;
		}
		return true;
	}

	public Resource downloadCompetitorSheet() {
		String fileName = "Modelo.xlsx";
		try {
			Path resourcesPath = ResourceUtils.getFile("classpath:files").toPath();
			Path filePath = resourcesPath.resolve(fileName);
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new RuntimeException("Arquivo " + fileName + " não encontrado");
			}
		} catch (MalformedURLException murl) {
			throw new RuntimeException("Arquivo " + fileName + " não encontrado", murl);
		} catch (IOException ioe) {
			throw new RuntimeException("Erro ao buscar arquivo " + fileName, ioe);
		}
	}

}
