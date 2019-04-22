package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.excel_io.exceptions.*;
import br.edu.opi.manager.excel_io.models.StudentTableMetadata;
import br.edu.opi.manager.excel_io.models.StudentTableRow;
import br.edu.opi.manager.excel_io.repositories.StudentTableMetadataRepository;
import br.edu.opi.manager.excel_io.repositories.StudentTableRowRepository;
import br.edu.opi.manager.school.models.Grade;
import br.edu.opi.manager.school.models.School;
import br.edu.opi.manager.student.models.Genre;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ParserStudentsService {

	private static final int CELLS_LENGTH = 5;

	private StudentTableMetadataRepository studentTableMetadataRepository;

	private StudentTableRowRepository studentTableRowRepository;

	@Autowired
	public ParserStudentsService(
			StudentTableMetadataRepository studentTableMetadataRepository,
			StudentTableRowRepository studentTableRowRepository) {
		this.studentTableMetadataRepository = studentTableMetadataRepository;
		this.studentTableRowRepository = studentTableRowRepository;
	}

	public void createStudents(Long schoolId, int year, MultipartFile multipartFile) {
		try {
			createStudentsTransactional(schoolId, year, multipartFile.getInputStream());
		} catch (IOException e) {
			throw new InvalidFileRuntimeException();
		}
	}

	@Transactional
	void createStudentsTransactional(Long schoolId, int year, InputStream excelFileInputStream) {
		StudentTableMetadata studentTableMetadata = new StudentTableMetadata(year, new School(schoolId));
		StudentTableMetadata savedStudentTableMetadata = studentTableMetadataRepository.save(studentTableMetadata);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
			XSSFSheet worksheet = workbook.getSheetAt(0); // TODO: verify number of sheets with client
			for (int i = 1; i <= worksheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = worksheet.getRow(i);
				StudentTableRow tempStudent = new StudentTableRow();
				if (isCellsWithContent(row)) {
					tempStudent.setName(getNameAttribute(row.getCell(0)));
					tempStudent.setDateBirth(getDateAttribute(row.getCell(1)));
					tempStudent.setGenre(getGenreAttribute(row.getCell(2)));
					tempStudent.setGrade(getGradeAttribute(row.getCell(3)));
					tempStudent.setScore(getScoreAttibute(row.getCell(4)));
					tempStudent.setStudentTableMetadata(savedStudentTableMetadata);
					savedStudentTableMetadata.addRow(tempStudent);
				}
			}
			studentTableMetadataRepository.save(savedStudentTableMetadata);
			new ConsolidateChangesInStudents(savedStudentTableMetadata.getRows()).start();
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
				return StudentTableRow.MISSED_STUDENT;
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

}
