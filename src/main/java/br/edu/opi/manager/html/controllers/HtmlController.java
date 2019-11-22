package br.edu.opi.manager.html.controllers;

import br.edu.opi.manager.html.dtos.HtmlDTO;
import br.edu.opi.manager.html.models.Html;
import br.edu.opi.manager.html.services.HtmlService;
import br.edu.opi.manager.project_patterns.dtos.AppControllerBase;
import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RestConstants.HTML_URI)
@Api(tags = "Html")
@CrossOrigin
public class HtmlController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlController.class.getSimpleName());

	private AppControllerBase appControllerBase;

	private HtmlService htmlService;

	@Autowired
	public HtmlController(AppControllerBase appControllerBase, HtmlService htmlService) {
		this.appControllerBase = appControllerBase;
		this.htmlService = htmlService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_HTML + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Create HTML")
	public ResponseEntity<?> create(@Valid @RequestBody HtmlDTO html) {
		LOGGER.info("trying create new html");
		Html savedHtml = htmlService.create(appControllerBase.mapTo(html, Html.class));
		// @formatter:off
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedHtml.getId())
				.toUri();
		LOGGER.info("htmml " + html.getId() + " create at " + location);
		return ResponseEntity.created(location).build();
		// @formatter:on
	}

	@PreAuthorize("hasAuthority('" + Privilege.INDEX_HTML + "')")
	@ApiOperation(value = "Get All HTML")
	@GetMapping({"/", ""})
	public List<HtmlDTO> index() {
		LOGGER.info("index html");
		Type type = new TypeToken<List<HtmlDTO>>() {
		}.getType();
		return appControllerBase.toList(htmlService.index(), type);
	}

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_HTML + "')")
	@ApiOperation(value = "Get HTML")
	@GetMapping({"/{id}/", "/{id}"})
	public HtmlDTO show(@PathVariable("id") String id) {
		LOGGER.info("show html " + id);
		return appControllerBase.mapTo(htmlService.show(id), HtmlDTO.class);
	}

	@PreAuthorize("hasAuthority('" + Privilege.UPDATE_HTML + "')")
	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Updates HTML")
	public ResponseEntity<?> update(
			@Min(value = 1) @PathVariable("id") String id,
			@Valid @RequestBody HtmlDTO htmlDTO) {
		Html html = appControllerBase.mapTo(htmlDTO, Html.class);
		LOGGER.info("trying update html");
		htmlService.update(id, html);
		LOGGER.info("html updated");
		return ResponseEntity.noContent().build();
	}

}
