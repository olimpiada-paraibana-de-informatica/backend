package br.edu.opi.manager.school.controller;

import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.school.dto.SchoolIO;
import br.edu.opi.manager.school.dto.SchoolInput;
import br.edu.opi.manager.school.dto.SchoolOutput;
import br.edu.opi.manager.school.model.School;
import br.edu.opi.manager.school.service.SchoolService;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;

@RestController
@RequestMapping(RestConstants.SCHOOL_URI)
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

//	@PreAuthorize("hasAuthority('" + Privilege.CREATE_SCHOOL + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Create a School", notes = "Also returns a link to retrieve the saved School in the location header")
	public ResponseEntity<?> create(@Valid @RequestBody SchoolInput schoolInput) {
		LOGGER.info("trying create new school " + schoolInput.getSchoolName());
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

	@PreAuthorize("hasAuthority('" + Privilege.INDEX_SCHOOL + "')")
	@ApiOperation(value = "Get All Schools")
	@GetMapping({"/", ""})
	// @formatter:off
	public Page<SchoolOutput> index(
			@RequestParam(required = false, name = "page") Integer page,
			@RequestParam(required = false, name = "size") Integer size) {
		LOGGER.info("index schools");
		return schoolIO.toPage(schoolService.index(page, size));
	}
	// @formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_SCHOOL + "')")
	@ApiOperation(value = "Get a User")
	@GetMapping({"/{id}/", "/{id}"})
	// @formatter:off
	public SchoolOutput show(
			@PathVariable("id") Long id) {
		LOGGER.info("show user " + id);
		return schoolIO.mapTo(schoolService.show(id));
	}

	@PreAuthorize("hasAnyAuthority('" + Privilege.UPDATE_SCHOOL + "', '" + Privilege.UPDATE_DELEGATE + "')")
	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Updates a delegate")
	public ResponseEntity<?> update(
			//@formatter:off
			@Min(value = 1) @PathVariable("id") Long id,
			@Valid @RequestBody SchoolInput schoolInput) {
		School school = schoolIO.mapTo(schoolInput);
		LOGGER.info("trying update school " + school.getName());
		schoolService.update(id, school);
		LOGGER.info("school " + school.getName() + " updated");
		return ResponseEntity.noContent().build();
	}

	//@formatter:on

	@PreAuthorize("hasAnyAuthority('" + Privilege.DELETE_SCHOOL + "', '" + Privilege.DELETE_DELEGATE + "')")
	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delete a School")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		LOGGER.info("trying deleting school " + id);
		schoolService.delete(id);
		LOGGER.info("school " + id + " deleted");
		return ResponseEntity.ok().build();
	}
	// @formatter:on

}
