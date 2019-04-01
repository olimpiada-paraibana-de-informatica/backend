package br.edu.opi.manager.places.controller;

import br.edu.opi.manager.places.dto.CityIO;
import br.edu.opi.manager.places.dto.CityOutput;
import br.edu.opi.manager.places.service.CityService;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConstants.CITY_URI)
@Api(tags = "Places")
@CrossOrigin
public class CityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class.getSimpleName());

	private CityIO cityIO;

	private CityService cityService;

	@Autowired
	public CityController(
			CityIO cityIO,
			CityService cityService) {
		this.cityIO = cityIO;
		this.cityService = cityService;
	}

	@ApiOperation(value = "Get all Cities")
	@GetMapping({"/", ""})
	public List<CityOutput> index(
			@RequestParam(value = "name", required = false) String namePartial,
			@RequestParam(value = "stateAbbreviation", required = false) String stateAbbreviation,
			@RequestParam(value = "stateCbo", required = false) Long stateCbo) {
		//@formatter:off
		LOGGER.info("index cities");
		return cityIO.toList(cityService.index(namePartial, stateAbbreviation, stateCbo));
		//@formatter:on
	}

	@ApiOperation(value = "Get a City by CBO code")
	@GetMapping({"/{id}/", "/{id}"})
	public CityOutput show(@PathVariable("id") Long id) {
		LOGGER.info("show city by cbo code");
		return cityIO.mapTo(cityService.show(id));
	}

}
