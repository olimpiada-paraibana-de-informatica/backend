package br.edu.opi.manager.olympiad.controllers;

import br.edu.opi.manager.olympiad.services.OpiLevelTwoClassifierService;
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
import java.math.BigDecimal;

@RestController
@RequestMapping(RestConstants.OPI_CLASSIFIER_URI)
@Api(tags = "Classifier")
@CrossOrigin
public class OpiLevelTwoClassifierController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpiLevelTwoClassifierController.class.getSimpleName());

	private OpiLevelTwoClassifierService opiLevelTwoClassifierService;

	@Autowired
	public OpiLevelTwoClassifierController(OpiLevelTwoClassifierService opiLevelTwoClassifierService) {
		this.opiLevelTwoClassifierService = opiLevelTwoClassifierService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.OPI_CLASSIFIER + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Updates all Competitors classifieds to level two")
	public ResponseEntity<?> classifier(@RequestParam(name = "percentage") @Valid @DecimalMin("0") @DecimalMax("1") BigDecimal percentageConsidered) {
		LOGGER.info("classifier competitors");
		opiLevelTwoClassifierService.levelTwoClassifier(percentageConsidered.doubleValue());
		return ResponseEntity.noContent().build();
	}

}
