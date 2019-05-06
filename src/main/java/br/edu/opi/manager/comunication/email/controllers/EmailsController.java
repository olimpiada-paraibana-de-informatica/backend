package br.edu.opi.manager.comunication.email.controllers;

import br.edu.opi.manager.comunication.email.dtos.EmailInput;
import br.edu.opi.manager.comunication.email.dtos.EmailOutput;
import br.edu.opi.manager.comunication.email.models.InterestedNewDelegates;
import br.edu.opi.manager.comunication.email.services.InterestedNewDelegatesService;
import br.edu.opi.manager.comunication.email.services.InterestedNewScoresService;
import br.edu.opi.manager.project_patterns.dtos.AppControllerBase;
import br.edu.opi.manager.project_patterns.models.user.Privilege;
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
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RestConstants.EMAILS_LIST_URI)
@Api(tags = "E-mails")
@CrossOrigin
public class EmailsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailsController.class.getSimpleName());

	private AppControllerBase appControllerBase;

	private InterestedNewDelegatesService interestedNewDelegatesService;

	private InterestedNewScoresService interestedNewScoresService;

	@Autowired
	public EmailsController(
			AppControllerBase appControllerBase,
			InterestedNewDelegatesService interestedNewDelegatesService,
			InterestedNewScoresService interestedNewScoresService) {
		this.appControllerBase = appControllerBase;
		this.interestedNewDelegatesService = interestedNewDelegatesService;
		this.interestedNewScoresService = interestedNewScoresService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_EMAILS_LIST + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Creates list of interested in new Delegates")
	public ResponseEntity<Object> create(@Valid @RequestBody List<EmailInput> emails) {
		LOGGER.info("trying create new emails list");
		List<InterestedNewDelegates> list = appControllerBase.toList(emails, InterestedNewDelegates.class);
		//interestedNewDelegatesService.create(list);
		LOGGER.info("emails list created");
		String uri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		return ResponseEntity.created(URI.create(uri)).build();
	}

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_DELEGATE + "')")
	@ApiOperation(value = "Get an only interested")
	@GetMapping({"/{id}/", "/{id}"})
	public EmailOutput show(@PathVariable("id") Long id) {
		LOGGER.info("show interested");
		return appControllerBase.mapTo(interestedNewDelegatesService.show(id), EmailOutput.class);
	}

	@PreAuthorize("hasAuthority('" + Privilege.UPDATE_DELEGATE + "')")
	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Updates an interested")
	public ResponseEntity<?> update(
			@Min(value = 1) @PathVariable("id") Long id,
			@Valid @RequestBody EmailInput emailInput) {
		InterestedNewDelegates interested = appControllerBase.mapTo(emailInput, InterestedNewDelegates.class);
		LOGGER.info("trying update interested " + interested.getEmail());
		interestedNewDelegatesService.update(id, interested);
		LOGGER.info("interested " + interested.getEmail() + " updated");
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAuthority('" + Privilege.DELETE_DELEGATE + "')")
	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delete an Interested")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		LOGGER.info("trying deleting interested " + id);
		interestedNewDelegatesService.delete(id);
		LOGGER.info("interested " + id + " deleted");
		return ResponseEntity.ok().build();
	}

}