package br.edu.opi.manager.olympiad.controllers;

import br.edu.opi.manager.olympiad.dtos.InternationalChampionOutput;
import br.edu.opi.manager.olympiad.services.InternationalChampionService;
import br.edu.opi.manager.places.dtos.StateOutput;
import br.edu.opi.manager.project_patterns.dtos.AppControllerBase;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(RestConstants.OPI_INTERNATIONAL_CHAMPIONS_URI)
@Api(tags = "Graphs")
@CrossOrigin
public class InternationalChampionsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternationalChampionsController.class.getSimpleName());

	private InternationalChampionService internationalChampionService;

	private AppControllerBase appControllerBase;

	@Autowired
	public InternationalChampionsController(
			InternationalChampionService internationalChampionService,
			AppControllerBase appControllerBase) {
		this.internationalChampionService = internationalChampionService;
		this.appControllerBase = appControllerBase;
	}

	@GetMapping({"/", ""})
	@ApiOperation(value = "Index International Champions")
	public List<InternationalChampionOutput> indexInternationalChampion() {
		LOGGER.info("index international champions");
		Type type = new TypeToken<List<InternationalChampionOutput>>() {}.getType();
		return appControllerBase.toList(internationalChampionService.indexByOrderByYear(), type);
	}

}
