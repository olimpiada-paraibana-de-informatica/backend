package br.edu.opi.manager.office_io.services;

import br.edu.opi.manager.office_io.exceptions.*;
import br.edu.opi.manager.office_io.models.CompetitorTableMetadata;
import br.edu.opi.manager.office_io.models.CompetitorTableRow;
import br.edu.opi.manager.office_io.models.StudentTableMetadata;
import br.edu.opi.manager.office_io.models.StudentTableRow;
import br.edu.opi.manager.office_io.repositories.CompetitorTableMetadataRepository;
import br.edu.opi.manager.office_io.repositories.CompetitorTableRowRepository;
import br.edu.opi.manager.office_io.repositories.StudentTableMetadataRepository;
import br.edu.opi.manager.office_io.repositories.StudentTableRowRepository;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ExcelParserService {

	private static String STUDENTS_FILE_NAME;
	private static String COMPETITORS_FILE_NAME;
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
			@Value("${xlsx.file.students}") String studentsFileName,
			@Value("${xlsx.file.competitors}") String competitorsFileName,
			@Value("${xlsx.cells.students}") int cellsStudentsLength,
			@Value("${xlsx.cells.competitors}") int cellsCompetitorsLength) {
		this.schoolService = schoolService;
		this.competitorTableMetadataRepository = competitorTableMetadataRepository;
		this.studentTableMetadataRepository = studentTableMetadataRepository;
		this.STUDENTS_FILE_NAME = studentsFileName;
		this.COMPETITORS_FILE_NAME = competitorsFileName;
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

	public void handleLevelTwo(Long schoolId, int year, MultipartFile multipartFile) {
		try {
			School school = schoolService.show(schoolId);
			updateCompetitorsTransactional(school, year, multipartFile.getInputStream());
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

	@Transactional
	void updateCompetitorsTransactional(School school, int year, InputStream excelFileInputStream) {
		CompetitorTableMetadata competitorTableMetadata = new CompetitorTableMetadata(year, new School(school.getId()));
		CompetitorTableMetadata savedCompetitorTableMetadata = competitorTableMetadataRepository.save(competitorTableMetadata);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				XSSFSheet worksheet = workbook.getSheetAt(i);
				for (int j = 1; j <= worksheet.getPhysicalNumberOfRows(); j++) {
					XSSFRow row = worksheet.getRow(j);
					CompetitorTableRow tempCompetitor = new CompetitorTableRow();
					if (isCellsLevelTwoWithContent(row, TargetXlsx.COMPETITOR)) {
						tempCompetitor.setHash(getHashAttribute(row.getCell(0)));
						tempCompetitor.setName(getNameAttribute(row.getCell(1)));
						tempCompetitor.setDateBirth(getDateAttribute(row.getCell(2)));
						tempCompetitor.setGenre(getGenreAttribute(row.getCell(3)));
						tempCompetitor.setGrade(getGradeAttribute(row.getCell(4)));
						tempCompetitor.setScore(getScoreAttibute(row.getCell(5)));
						tempCompetitor.setCompetitorTableMetadata(savedCompetitorTableMetadata);
						savedCompetitorTableMetadata.addRow(tempCompetitor);
					}
				}
				competitorTableMetadataRepository.save(savedCompetitorTableMetadata);
//				schoolService.update(school.getId(), true);
//				new ConsolidateChangesInCompetitors(schoolId, savedCompetitorTableMetadata.getRows()).start();
				new UpdateCompetitorsLevelTwo(savedCompetitorTableMetadata.getRows()).run(); // TODO: thread safe
			}
		} catch (IOException e) {
			throw new InvalidFileRuntimeException();
		}
	}

	private static Double getHashAttribute(XSSFCell cell) {
		try {
			Double hash = cell.getNumericCellValue();
			if (hash == null) {
				throw new HashNotNullRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1);
			}
			return hash;
		} catch (HashNotNullRuntimeException nnnre) {
			throw nnnre;
		} catch (Exception e) {
			throw new InvalidHashRuntimeException(cell.getColumnIndex(), cell.getRow().getRowNum() + 1, cell.toString());
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

	private static boolean isCellsLevelTwoWithContent(
			XSSFRow row,
			TargetXlsx targetXlsx) {
		int cellsLength = targetXlsx == TargetXlsx.COMPETITOR ? CELLS_COMPETITORS_LENGTH : CELLS_STUDENTS_LENGTH;
		if (row == null || row.getCell(0) == null || row.getCell(0).getRawValue() == null || row.getLastCellNum() < cellsLength + 1) {
			return false;
		}
		return true;
	}

	public Resource downloadSheet(TargetXlsx targetXlsx) {
		String fileName = solveFileName(targetXlsx);
		try {
			Path filePath = null;
			if (fileName.startsWith("http")) {
				URL url = new URL(fileName);
				ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
				Path temp = Files.createTempFile("Renomear_Modelo", ".xlsx");
				FileOutputStream fileOutputStream = new FileOutputStream(temp.toString());
				FileChannel fileChannel = fileOutputStream.getChannel();
				fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE); // TODO: improve this...
				filePath = temp.toAbsolutePath();
			} else {
				Path resourcesPath = ResourceUtils.getFile("classpath:files").toPath();
				filePath = resourcesPath.resolve(fileName);
			}
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
				return STUDENTS_FILE_NAME;
			default:
				return COMPETITORS_FILE_NAME;
		}
	}

}
