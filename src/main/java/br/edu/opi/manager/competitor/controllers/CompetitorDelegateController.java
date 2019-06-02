package br.edu.opi.manager.competitor.controllers;

import br.edu.opi.manager.competitor.dtos.CompetitorIO;
import br.edu.opi.manager.competitor.dtos.CompetitorInput;
import br.edu.opi.manager.competitor.dtos.CompetitorOutput;
import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.competitor.services.CompetitorService;
import br.edu.opi.manager.project_patterns.models.user.Privilege;
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

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.security.Principal;

@RestController
@RequestMapping(RestConstants.COMPETITOR_DELEGATE_URI)
@Api(tags = "Competitor")
@CrossOrigin
public class CompetitorDelegateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompetitorDelegateController.class.getSimpleName());

	private CompetitorIO competitorIO;

	private CompetitorService competitorService;

	@Autowired
	public CompetitorDelegateController(CompetitorIO competitorIO, CompetitorService competitorService) {
		this.competitorIO = competitorIO;
		this.competitorService = competitorService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_ASSOCIATED_COMPETITOR + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Create a Competitor")
	public ResponseEntity<?> create(@Valid @RequestBody CompetitorInput competitorInput, Principal principal) {
		LOGGER.info("delegate trying create new associated competitor");
		Competitor competitor = competitorIO.mapTo(competitorInput);
		competitorService.create(competitor, principal.getName());
		LOGGER.info("associated competitor created");
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAuthority('" + Privilege.INDEX_ASSOCIATED_COMPETITOR + "')")
	@ApiOperation(value = "Get All Competitors")
	@GetMapping({"/", ""})
	public Page<CompetitorOutput> index(
			@RequestParam(required = false, name = "page") Integer page,
			@RequestParam(required = false, name = "size") Integer size,
			@RequestParam(required = false, name = "level") String level,
			@RequestParam(required = false, name = "category") String category,
			Principal principal) {
		LOGGER.info("delegate index associated competitors");
		return competitorIO.toPage(competitorService.index(page, size, level, category, principal.getName()));
	}

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_ASSOCIATED_COMPETITOR + "')")
	@ApiOperation(value = "Get a Competitor")
	@GetMapping({"/{id}/", "/{id}"})
	public CompetitorOutput show(@PathVariable("id") Long id, Principal principal) {
		LOGGER.info("delegate show associated competitor " + id);
		return competitorIO.mapTo(competitorService.show(id, principal.getName()));
	}

	@PreAuthorize("hasAuthority('" + Privilege.UPDATE_ASSOCIATED_COMPETITOR + "')")
	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Updates a Competitor")
	public ResponseEntity<?> update(
			//@formatter:off
			@Min(value = 1) @PathVariable("id") Long id,
			@Valid @RequestBody CompetitorInput competitorInput, Principal principal) {
		Competitor competitor = competitorIO.mapTo(competitorInput);
		LOGGER.info("delegate trying update associated competitor");
		competitorService.update(id, competitor, principal.getName());
		LOGGER.info("associated competitor updated");
		return ResponseEntity.noContent().build();
	}

	//@formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.DELETE_ASSOCIATED_COMPETITOR + "')")
	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delete a Competitor")
	public ResponseEntity<?> delete(@PathVariable("id") Long id, Principal principal) {
		LOGGER.info("delegate trying deleting associated competitor " + id);
		competitorService.delete(id, principal.getName());
		LOGGER.info("associated competitor " + id + " deleted");
		return ResponseEntity.ok().build();
	}

}
