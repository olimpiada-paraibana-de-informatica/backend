package br.edu.opi.manager.delegate.controller;

import br.edu.opi.manager.delegate.dto.DelegateIO;
import br.edu.opi.manager.delegate.dto.DelegateInput;
import br.edu.opi.manager.delegate.dto.DelegateOutput;
import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.delegate.service.DelegateService;
import br.edu.opi.manager.project_patterns.dto.AppControllerBase;
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

@RestController
@RequestMapping(RestConstants.DELEGATE_URI)
@Api(tags = "Delegate")
@CrossOrigin
public class DelegateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DelegateController.class.getSimpleName());

	private AppControllerBase appControllerBase;

	private DelegateService delegateService;

	private DelegateIO delegateIO;

	@Autowired
	public DelegateController(
			AppControllerBase appControllerBase,
			DelegateService delegateService,
			DelegateIO delegateIO) {
		this.appControllerBase = appControllerBase;
		this.delegateService = delegateService;
		this.delegateIO = delegateIO;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_DELEGATE + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Create a Delegate", notes = "Also returns a link to retrieve the saved Delegate in the location header")
	public ResponseEntity<Object> create(@Valid @RequestBody DelegateInput delegateInput) {
		LOGGER.info("trying create new delegate " + delegateInput.getName());
		Delegate delegate = delegateIO.mapTo(delegateInput);
		Delegate savedDelegate = delegateService.create(delegate);
		//@formatter:off
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedDelegate.getId())
				.toUri();
		LOGGER.info("delegate " + delegate.getId() + " create at " + location);
		return ResponseEntity.created(location).build();
		//@formatter:on
	}

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_DELEGATE + "')")
	@ApiOperation(value = "Get an only Delegate")
	@GetMapping({"/{id}/", "/{id}"})
	public DelegateOutput showDelegate(@PathVariable("id") Long id) {
		LOGGER.info("show delegate");
		return appControllerBase.mapTo(delegateService.show(id), DelegateOutput.class);
	}

	@PreAuthorize("hasAuthority('" + Privilege.UPDATE_DELEGATE + "')")
	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Updates a delegate")
	public ResponseEntity<?> updateDelegate(
			//@formatter:off
			@Min(value = 1) @PathVariable("id") Long id,
			@Valid @RequestBody DelegateInput delegateInput) {
		Delegate delegate = delegateIO.mapTo(delegateInput);
		LOGGER.info("trying update delegate " + delegate.getUsername());
		delegateService.update(id, delegate);
		LOGGER.info("delegate " + delegate.getUsername() + " updated");
		return ResponseEntity.noContent().build();
	}

	//@formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.DELETE_DELEGATE + "')")
	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delete a Deçegate")
	public ResponseEntity<?> deleteDelegate(@PathVariable("id") Long id) {
		LOGGER.info("trying deleting delegate " + id);
		delegateService.delete(id);
		LOGGER.info("delegate " + id + " deleted");
		return ResponseEntity.ok().build();
	}

}