package br.edu.opi.manager.olympiad.controllers;

import br.edu.opi.manager.olympiad.dtos.ResultCategoryOutput;
import br.edu.opi.manager.olympiad.dtos.ResultsCategoryAndYearsOutput;
import br.edu.opi.manager.olympiad.models.OpiCategory;
import br.edu.opi.manager.olympiad.services.OpiResultsService;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConstants.OPI_RESULTS_URI)
@Api(tags = "Results")
@CrossOrigin
public class OpiResultsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpiResultsController.class.getSimpleName());

	private OpiResultsService opiResultsService;

	@Autowired
	public OpiResultsController(OpiResultsService opiResultsService) {
		this.opiResultsService = opiResultsService;
	}

	@GetMapping({"/category-years/", "/category-years"})
	@ApiOperation(value = "Year's table of results by categories")
	public List<ResultsCategoryAndYearsOutput> resultsCategoriesAndYears() {
		LOGGER.info("results categories and yours years");
		return opiResultsService.resultsYears();
	}

	@GetMapping({"/champions/{category}/", "/champions/{category}"})
	@ApiOperation(value = "Year's table of results by categories")
	public List<ResultCategoryOutput> resultsCategoriesAndYears(
			@PathVariable("category") String category) {
		LOGGER.info("all champions in category" + category);
		OpiCategory opiCategory = null;
		switch (category) {
			case "iniciacao1":
				opiCategory = OpiCategory.INICIACAO_1;
				break;
			case "iniciacao1Pub":
				opiCategory = OpiCategory.INICIACAO_1_PUB;
				break;
			case "iniciacao2":
				opiCategory = OpiCategory.INICIACAO_2;
				break;
			case "iniciacao2Pub":
				opiCategory = OpiCategory.INICIACAO_1_PUB;
				break;
			case "programacao":
				opiCategory = OpiCategory.PROGRAMACAO;
				break;
			case "avancadoJunior":
				opiCategory = OpiCategory.AVANCADO_JUNIOR;
				break;
			case "avancadoSenior":
				opiCategory = OpiCategory.AVANCADO_SENIOR;
				break;
		}
		assert opiCategory != null;
		return opiResultsService.resultCategory(opiCategory);
	}

}
