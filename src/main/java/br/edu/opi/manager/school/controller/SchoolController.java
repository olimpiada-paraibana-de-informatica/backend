package br.edu.opi.manager.school.controller;

import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.school.dto.SchoolIO;
import br.edu.opi.manager.school.dto.SchoolInput;
import br.edu.opi.manager.school.model.School;
import br.edu.opi.manager.school.service.SchoolService;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(RestConstants.SCHOLL_URI)
@Api(tags = "School")
@CrossOrigin
public class SchoolController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolController.class.getSimpleName());

	private SchoolIO schoolIO;

	private SchoolService schoolService;

	@Autowired
	public SchoolController(
			SchoolIO schoolIO,
			SchoolService schoolService) {
		this.schoolIO = schoolIO;
		this.schoolService = schoolService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_USER + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Create a School", notes = "Also returns a link to retrieve the saved School in the location header")
	public ResponseEntity<Object> create(@Valid @RequestBody SchoolInput schoolInput) {
		LOGGER.info("trying create new school " + schoolInput.getName());
		School school = schoolIO.mapTo(schoolInput);
		School savedSchool = schoolService.create(school);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedSchool.getId())
				.toUri();
		LOGGER.info("school " + school.getId() + " create at " + location);
		return ResponseEntity.created(location).build();
	}

}