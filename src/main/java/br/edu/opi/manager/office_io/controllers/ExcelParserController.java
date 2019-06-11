package br.edu.opi.manager.office_io.controllers;

import br.edu.opi.manager.office_io.services.ExcelGenerateService;
import br.edu.opi.manager.office_io.services.ExcelParserService;
import br.edu.opi.manager.office_io.services.TargetXlsx;
import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping(RestConstants.OFFICE_DELEGATE_URI)
@Api(tags = "Office")
@CrossOrigin
public class ExcelParserController {

	private ExcelParserService excelParserService;
	private ExcelGenerateService excelGenerateService;

	@Autowired
	public ExcelParserController(
			ExcelParserService excelParserService,
			ExcelGenerateService excelGenerateService) {
		this.excelParserService = excelParserService;
		this.excelGenerateService = excelGenerateService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_ASSOCIATED_COMPETITOR + "')")
	@PostMapping({"/schools/competitors/", "/schools/competitors"})
	@ApiOperation(value = "Upload excel files to register competitors")
	public void createCompetitorsFromSheet(@RequestParam("file") MultipartFile multipartFile, Principal principal) {
		excelParserService.createCompetitors(principal.getName(), LocalDate.now().getYear(), multipartFile);
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_ASSOCIATED_STUDENT + "')")
	@PostMapping({"/schools/students/", "/schools/students"})
	@ApiOperation(value = "Upload excel files to register competitors")
	public void createStudentsFromSheet(@RequestParam("file") MultipartFile multipartFile, Principal principal) {
		excelParserService.createStudents(principal.getName(), LocalDate.now().getYear(), multipartFile);
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_COMPETITOR + "')")
	@PostMapping({"/schools/{id}/level_two/", "/schools/{id}/level_two"})
	@ApiOperation(value = "Upload excel files to update score of competitors level two")
	public void updateCompetitorsLevelTwoFromSheet(@PathVariable("id") Long schoolId, @RequestParam("file") MultipartFile multipartFile) {
		excelParserService.handleLevelTwo(schoolId, LocalDate.now().getYear(), multipartFile);
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_ASSOCIATED_STUDENT + "')")
	@GetMapping({"/schools/students/download/", "/schools/students/download"})
	@ApiOperation(value = "Download excel student model")
	public ResponseEntity<Resource> downloadStudentSheet() {
		Resource resource = excelParserService.downloadSheet(TargetXlsx.STUDENT);
		String contentType = "application/octet-stream"; // competitorParserService.XLSX_CONTENT_TYPE;
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_ASSOCIATED_COMPETITOR + "')")
	@GetMapping({"/schools/competitors/download/", "/schools/competitors/download"})
	@ApiOperation(value = "Download excel competitor model")
	public ResponseEntity<Resource> downloadCompetitorSheet() {
		Resource resource = excelParserService.downloadSheet(TargetXlsx.COMPETITOR);
		String contentType = "application/octet-stream"; // competitorParserService.XLSX_CONTENT_TYPE;
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_COMPETITOR + "')")
	@GetMapping({"/schools/{id}/level_two/download/", "/schools/{id}/level_two/download"})
	@ApiOperation(value = "Download excel school's competitors on level two")
	public ResponseEntity<Resource> downloadCompetitorLevelTwoSheet(@PathVariable("id") Long schoolId) {
		Resource resource = excelGenerateService.generateXlsxCompetitorsOnLevelTwo(schoolId);
		String contentType = "application/octet-stream"; // competitorParserService.XLSX_CONTENT_TYPE;
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
