package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.competitor.exceptions.WriteFileRuntimeException;
import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import br.edu.opi.manager.excel_io.exceptions.CreateFileRuntimeException;
import br.edu.opi.manager.olympiad.models.OpiCategory;
import br.edu.opi.manager.olympiad.models.OpiLevels;
import br.edu.opi.manager.school.models.School;
import br.edu.opi.manager.school.services.SchoolService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.LinkedHashSet;

@Service
public class ExcelGenerateService {

	public final String COMPETITORS_COLUMN_ID = "#";
	public static final String COMPETITORS_COLUMN_NAME = "Nome";
	public static final String COMPETITORS_COLUMN_DATE_BIRTH = "Data Nascimento";
	public static final String COMPETITORS_COLUMN_GENRE = "Gênero";
	public static final String COMPETITORS_COLUMN_GRADE = "Série";
	public static final String COMPETITORS_COLUMN_SCORE = "Pontuação";

	private SchoolService schoolService;
	private CompetitorRepository competitorRepository;

	@Autowired
	public ExcelGenerateService(
			SchoolService schoolService,
			CompetitorRepository competitorRepository) {
		this.schoolService = schoolService;
		this.competitorRepository = competitorRepository;
	}

	public Resource generateXlsxCompetitorsOnLevelTwo(Long schoolId) {
		try {
			School school = schoolService.show(schoolId);
			Path filePath = Files.createTempFile("Renomear_Segunda_Fase", ".xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook();
			for (OpiCategory category : OpiCategory.values()) {
				LinkedHashSet<Competitor> competitors = new LinkedHashSet<>(
						competitorRepository.findAllByStudentSchoolIdAndCategoryAndLevelAndYear(
								school.getId(),
								category,
								OpiLevels.TWO,
								LocalDate.now().getYear(),
								Sort.by(Sort.Order.asc("student.person.fullName"))));
				if (competitors == null || competitors.isEmpty()) {
					continue;
				}
				generateXlsxCompetitorsOnLevelTwo(workbook, new FileOutputStream(filePath.toString()), category, competitors);
			}
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new RuntimeException("Erro ao processar arquivo");
			}
		} catch (IOException e) {
			throw new CreateFileRuntimeException();
		}
	}

	private void generateXlsxCompetitorsOnLevelTwo(XSSFWorkbook workbook, FileOutputStream fileOutputStream, OpiCategory category, LinkedHashSet<Competitor> competitors) {
		XSSFSheet sheet = workbook.createSheet(category.getName());

		XSSFRow rowHead = sheet.createRow(0);
		rowHead.createCell(0).setCellValue(COMPETITORS_COLUMN_ID);
		rowHead.createCell(1).setCellValue(COMPETITORS_COLUMN_NAME);
		rowHead.createCell(2).setCellValue(COMPETITORS_COLUMN_DATE_BIRTH);
		rowHead.createCell(3).setCellValue(COMPETITORS_COLUMN_GENRE);
		rowHead.createCell(4).setCellValue(COMPETITORS_COLUMN_GRADE);
		rowHead.createCell(5).setCellValue(COMPETITORS_COLUMN_SCORE);

		int rowCount = 1;
		for (Competitor competitor : competitors) {
			XSSFRow row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(competitor.getId());
			row.createCell(1).setCellValue(competitor.getFullName());
			row.createCell(2).setCellValue(competitor.getDateBirth());
			row.createCell(3).setCellValue(competitor.getGenre());
			row.createCell(4).setCellValue(competitor.getGrade().getName());
			row.createCell(5).setCellValue(-1.0);
		}

		try {
			workbook.write(fileOutputStream);
		} catch (IOException e) {
			throw new WriteFileRuntimeException();
		}
	}

}
