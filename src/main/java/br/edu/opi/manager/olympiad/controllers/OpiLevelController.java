package br.edu.opi.manager.olympiad.controllers;

import br.edu.opi.manager.olympiad.services.OpiLevelService;
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

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@RestController
@RequestMapping(RestConstants.OPI_LEVEL_URI)
@Api(tags = "Level")
@CrossOrigin
public class OpiLevelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpiLevelController.class.getSimpleName());

	private OpiLevelService opiLevelService;

	@Autowired
	public OpiLevelController(OpiLevelService opiLevelTwoClassifierService) {
		this.opiLevelService = opiLevelTwoClassifierService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.UPDATE_OPI_LEVEL + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Updates all Competitors classifieds to level two")
	public ResponseEntity<?> classifier(@RequestParam(name = "percentage") @Valid @DecimalMin("0") @DecimalMax("1") Integer percentageConsidered) {
		LOGGER.info("classifier competitors");
		opiLevelService.levelTwoClassifier(percentageConsidered);
		LOGGER.info("classifier competitors done");
		return ResponseEntity.noContent().build();
	}

}
