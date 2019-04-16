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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.security.Principal;

@RestController
@RequestMapping(RestConstants.SCHOOL_DELEGATE_URI)
@Api(tags = "School")
@CrossOrigin
public class SchoolDelegateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolDelegateController.class.getSimpleName());

	private SchoolIO schoolIO;

	private SchoolService schoolService;

	@Autowired
	public SchoolDelegateController(
			SchoolIO schoolIO,
			SchoolService schoolService) {
		this.schoolIO = schoolIO;
		this.schoolService = schoolService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_ASSOCIATED_SCHOOL + "')")
	@ApiOperation(value = "Delegate Get a School")
	@GetMapping({"/{id}/", "/{id}"})
	public SchoolOutput show(
			@PathVariable("id") Long id, Principal principal) {
		LOGGER.info("show user " + id);
		return schoolIO.mapTo(schoolService.show(id, principal.getName()));
	}

	@PreAuthorize("hasAnyAuthority('" + Privilege.UPDATE_ASSOCIATED_SCHOOL + "', '" + Privilege.UPDATE_DELEGATE + "')")
	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Updates a School")
	public ResponseEntity<?> update(
	//@formatter:off
			@Min(value = 1) @PathVariable("id") Long id,
			@Valid @RequestBody SchoolInput schoolInput, Principal principal) {
		School school = schoolIO.mapTo(schoolInput);
		LOGGER.info("trying update school " + school.getName());
		schoolService.update(id, school, principal.getName());
		LOGGER.info("school " + school.getName() + " updated");
		return ResponseEntity.noContent().build();
	}
	//@formatter:on

	@PreAuthorize("hasAnyAuthority('" + Privilege.DELETE_SCHOOL + "', '" + Privilege.DELETE_DELEGATE + "')")
	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delegate warns to delete his school")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		LOGGER.info("trying warns to delete school " + id);
		// TODO: implement
		return ResponseEntity.ok().build();
	}
	// @formatter:on

}
