package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.excel_io.exceptions.*;
import br.edu.opi.manager.excel_io.models.CompetitorTableMetadata;
import br.edu.opi.manager.excel_io.models.CompetitorTableRow;
import br.edu.opi.manager.excel_io.models.StudentTableMetadata;
import br.edu.opi.manager.excel_io.models.StudentTableRow;
import br.edu.opi.manager.excel_io.repositories.CompetitorTableMetadataRepository;
import br.edu.opi.manager.excel_io.repositories.CompetitorTableRowRepository;
import br.edu.opi.manager.excel_io.repositories.StudentTableMetadataRepository;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ExcelParserService {

	public static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	private static int CELLS_STUDENTS_LENGTH;

	private static int CELLS_COMPETITORS_LENGTH;

	private SchoolService schoolService;

	private CompetitorTableMetadataRepository competitorTableMetadataRepository;

	private StudentTableMetadataRepository studentTableMetadataRepository;

	@Autowired
	public ExcelParserService(
			SchoolService schoolService,
			CompetitorTableMetadataRepository competitorTableMetadataRepository,
			CompetitorTableRowRepository competitorTableRowRepository,
			StudentTableMetadataRepository studentTableMetadataRepository,
			StudentTableRowRepository studentTableRowRepository,
			@Value("${xlsx.cells.students}") int cellsStudentsLength,
			@Value("${xlsx.cells.competitors}") int cellsCompetitorsLength) {
		this.schoolService = schoolService;
		this.competitorTableMetadataRepository = competitorTableMetadataRepository;
		this.studentTableMetadataRepository = studentTableMetadataRepository;
		this.CELLS_STUDENTS_LENGTH = cellsStudentsLength;
		this.CELLS_COMPETITORS_LENGTH = cellsCompetitorsLength;
	}

	public void createStudents(String delegatePrincipal, int year, MultipartFile multipartFile) {
		try {
			School school = schoolService.show(delegatePrincipal);
			createStudentsTransactional(school.getId(), year, multipartFile.getInputStream());
		} catch (IOException e) {
			throw new InvalidFileRuntimeException();
		}
	}

	public void createCompetitors(String delegatePrincipal, int year, MultipartFile multipartFile) {
		try {
			School school = schoolService.show(delegatePrincipal);
			createCompetitorsTransactional(school, year, multipartFile.getInputStream());
		} catch (IOException e) {
			throw new InvalidFileRuntimeException();
		}
	}

	@Transactional
	void createStudentsTransactional(Long schoolId, int year, InputStream excelFileInputStream) {
		StudentTableMetadata competitorTableMetadata = new StudentTableMetadata(year, new School(schoolId));
		StudentTableMetadata savedCompetitorTableMetadata = studentTableMetadataRepository.save(competitorTableMetadata);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
			XSSFSheet worksheet = workbook.getSheetAt(0); // TODO: verify number of sheets with client
			for (int i = 1; i <= worksheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = worksheet.getRow(i);
				StudentTableRow tempStudent = new StudentTableRow();
				if (isCellsWithContent(row, TargetXlsx.STUDENT)) {
					tempStudent.setName(getNameAttribute(row.getCell(0)));
					tempStudent.setDateBirth(getDateAttribute(row.getCell(1)));
					tempStudent.setGenre(getGenreAttribute(row.getCell(2)));
					tempStudent.setStudentTableMetadata(savedCompetitorTableMetadata);
					savedCompetitorTableMetadata.addRow(tempStudent);
				}
			}
			studentTableMetadataRepository.save(savedCompetitorTableMetadata);
//			new ConsolidateChangesInStudents(schoolId, savedCompetitorTableMetadata.getRows()).start();
			new ConsolidateChangesInStudents(schoolId, savedCompetitorTableMetadata.getRows()).run();
		} catch (IOException e) {
			throw new InvalidFileRuntimeException();
		}
	}

	@Transactional
	void createCompetitorsTransactional(School school, int year, InputStream excelFileInputStream) {
		CompetitorTableMetadata competitorTableMetadata = new CompetitorTableMetadata(year, new School(school.getId()));
		CompetitorTableMetadata savedCompetitorTableMetadata = competitorTableMetadataRepository.save(competitorTableMetadata);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
			XSSFSheet worksheet = workbook.getSheetAt(0); // TODO: verify number of sheets with client
			for (int i = 1; i <= worksheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = worksheet.getRow(i);
				CompetitorTableRow tempCompetitor = new CompetitorTableRow();
				if (isCellsWithContent(row, TargetXlsx.COMPETITOR)) {
					tempCompetitor.setName(getNameAttribute(row.getCell(0)));
					tempCompetitor.setDateBirth(getDateAttribute(row.getCell(1)));
					tempCompetitor.setGenre(getGenreAttribute(row.getCell(2)));
					tempCompetitor.setGrade(getGradeAttribute(row.getCell(3)));
					tempCompetitor.setScore(getScoreAttibute(row.getCell(4)));
					tempCompetitor.setCompetitorTableMetadata(savedCompetitorTableMetadata);
					savedCompetitorTableMetadata.addRow(tempCompetitor);
				}
			}
			competitorTableMetadataRepository.save(savedCompetitorTableMetadata);
			schoolService.update(school.getId(), true);
//			new ConsolidateChangesInCompetitors(schoolId, savedCompetitorTableMetadata.getRows()).start();
			new ConsolidateChangesInCompetitors(school, savedCompetitorTableMetadata.getRows()).run();
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

	private static boolean isCellsWithContent(
			XSSFRow row,
			TargetXlsx targetXlsx) {
		int cellsLength = targetXlsx == TargetXlsx.COMPETITOR ? CELLS_COMPETITORS_LENGTH : CELLS_STUDENTS_LENGTH;
		if (row == null || row.getCell(0) == null || row.getCell(0).getRawValue() == null || row.getLastCellNum() < cellsLength) {
			return false;
		}
		return true;
	}

	public Resource downloadSheet(TargetXlsx targetXlsx) {
		String fileName = solveFileName(targetXlsx);
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

	private String solveFileName(TargetXlsx targetXlsx) {
		switch (targetXlsx) {
			case STUDENT:
				return "OPI_Modelo_Estudante.xlsx";
			default:
				return "OPI_Modelo_Competidor.xlsx";
		}
	}

}
