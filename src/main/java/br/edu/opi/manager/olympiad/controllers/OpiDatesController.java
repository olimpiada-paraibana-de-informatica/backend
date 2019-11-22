package br.edu.opi.manager.olympiad.controllers;

import br.edu.opi.manager.olympiad.dtos.OpiDatesDTO;
import br.edu.opi.manager.olympiad.models.OpiDates;
import br.edu.opi.manager.olympiad.services.OpiDatesService;
import br.edu.opi.manager.project_patterns.dtos.AppControllerBase;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestConstants.OPI_DATES_URI)
@Api(tags = "Dates")
@CrossOrigin
public class OpiDatesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpiDatesController.class.getSimpleName());

	private OpiDatesService opiDatesService;

	private AppControllerBase appControllerBase;

	@Autowired
	public OpiDatesController(
			OpiDatesService opiDatesService,
			AppControllerBase appControllerBase) {
		this.opiDatesService = opiDatesService;
		this.appControllerBase = appControllerBase;
	}

	@GetMapping({"/", ""})
	@ApiOperation(value = "Show Opi Dates")
	public OpiDatesDTO showOpiDates() {
		LOGGER.info("show opi dates");
		return appControllerBase.mapTo(opiDatesService.show(), OpiDatesDTO.class);
	}

	@PutMapping({"/", ""})
	@ApiOperation(value = "Update Opi Dates")
	public ResponseEntity<?> updateOpiDates(@RequestBody OpiDatesDTO opiDatesInput) {
		LOGGER.info("update opi dates");
		OpiDates opiDates = appControllerBase.mapTo(opiDatesInput, OpiDates.class);
		LOGGER.info("trying update opi dates");
		opiDatesService.update(opiDates);
		LOGGER.info("opi dates updated");
		return ResponseEntity.noContent().build();
	}

}
