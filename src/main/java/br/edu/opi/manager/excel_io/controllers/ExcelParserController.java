package br.edu.opi.manager.excel_io.controllers;

import br.edu.opi.manager.excel_io.services.ParserStudentsService;
import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping(RestConstants.EXCEL_DELEGATE_URI)
@Api(tags = "Excel")
@CrossOrigin
public class ExcelParserController {

	private ParserStudentsService parserStudentsService;

	@Autowired
	public ExcelParserController(ParserStudentsService parserStudentsService) {
		this.parserStudentsService = parserStudentsService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_ASSOCIATED_STUDENT + "')")
	@PostMapping({"/schools/competitors/", "/schools/competitors"})
	@ApiOperation(value = "Upload excel files to register competitors")
	public void createCompetitorsFromSheet(@RequestParam("file") MultipartFile multipartFile, Principal principal) {
		parserStudentsService.createStudents(principal.getName(), LocalDate.now().getYear(), multipartFile);
	}

}
