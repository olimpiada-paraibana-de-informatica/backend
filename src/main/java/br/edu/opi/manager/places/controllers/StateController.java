package br.edu.opi.manager.places.controllers;

import br.edu.opi.manager.places.dtos.StateOutput;
import br.edu.opi.manager.places.services.StateService;
import br.edu.opi.manager.project_patterns.dtos.AppControllerBase;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(RestConstants.STATE_URI)
@Api(tags = "Places")
@CrossOrigin
public class StateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StateController.class.getSimpleName());

	private AppControllerBase appControllerBase;

	private StateService stateService;

	@Autowired
	public StateController(
			AppControllerBase appControllerBase,
			StateService stateService) {
		this.appControllerBase = appControllerBase;
		this.stateService = stateService;
	}

	@ApiOperation(value = "Get All States")
	@GetMapping({"/", ""})
	public List<StateOutput> index() {
		//@formatter:off
		LOGGER.info("index states");
		Type type = new TypeToken<List<StateOutput>>() {}.getType();
		return appControllerBase.toList(stateService.index(), type);
		//@formatter:on
	}

	@ApiOperation(value = "Get a State")
	@GetMapping({"/{id}/", "/{id}"})
	public StateOutput show(@PathVariable("id") Long id) {
		LOGGER.info("show state " + id);
		return appControllerBase.mapTo(stateService.show(id), StateOutput.class);
	}

}
