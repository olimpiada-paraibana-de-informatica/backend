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

@RestController
@RequestMapping(RestConstants.COMPETITOR_URI)
@Api(tags = "Competitor")
@CrossOrigin
public class CompetitorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompetitorController.class.getSimpleName());

	private CompetitorIO competitorIO;

	private CompetitorService competitorService;

	@Autowired
	public CompetitorController(CompetitorIO competitorIO, CompetitorService competitorService) {
		this.competitorIO = competitorIO;
		this.competitorService = competitorService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_COMPETITOR + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Create a Competitor")
	public ResponseEntity<?> create(@Valid @RequestBody CompetitorInput competitorInput) {
		LOGGER.info("trying create new competitor");
		Competitor competitor = competitorIO.mapTo(competitorInput);
		competitorService.create(competitor);
		LOGGER.info("competitor created");
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAuthority('" + Privilege.INDEX_COMPETITOR + "')")
	@ApiOperation(value = "Get All Competitors")
	@GetMapping({"/", ""})
	public Page<CompetitorOutput> index(
			@RequestParam(required = false, name = "page") Integer page,
			@RequestParam(required = false, name = "size") Integer size,
			@RequestParam(required = false, name = "level") String level,
			@RequestParam(required = false, name = "category") String category) {
		LOGGER.info("index competitors");
		return competitorIO.toPage(competitorService.index(page, size, level, category));
	}

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_COMPETITOR + "')")
	@ApiOperation(value = "Get a Competitor")
	@GetMapping({"/{id}/", "/{id}"})
	public CompetitorOutput show(@PathVariable("id") Long id) {
		LOGGER.info("show competitor " + id);
		return competitorIO.mapTo(competitorService.show(id));
	}

	@PreAuthorize("hasAuthority('" + Privilege.UPDATE_COMPETITOR + "')")
	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Updates a Competitor")
	public ResponseEntity<?> update(
			//@formatter:off
			@Min(value = 1) @PathVariable("id") Long id,
			@Valid @RequestBody CompetitorInput competitorInput) {
		Competitor competitor = competitorIO.mapTo(competitorInput);
		LOGGER.info("trying update competitor");
		competitorService.update(id, competitor);
		LOGGER.info("competitor updated");
		return ResponseEntity.noContent().build();
	}

	//@formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.DELETE_COMPETITOR + "')")
	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delete a Competitor")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		LOGGER.info("trying deleting competitor " + id);
		competitorService.delete(id);
		LOGGER.info("competitor " + id + " deleted");
		return ResponseEntity.ok().build();
	}

}
