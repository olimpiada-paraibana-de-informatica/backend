package br.edu.opi.manager.olympiad.controllers;

import br.edu.opi.manager.olympiad.services.OpiGraphService;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstants.OPI_GRAPH_URI)
@Api(tags = "Graphs")
@CrossOrigin
public class OpiGraphController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpiGraphController.class.getSimpleName());

	private OpiGraphService opiGraphService;

	@Autowired
	public OpiGraphController(OpiGraphService opiGraphService) {
		this.opiGraphService = opiGraphService;
	}

	@GetMapping({"/obi/", "/obi"})
	@ApiOperation(value = "Show OBI graph")
	public String getObiGraph() {
		LOGGER.info("Show OBI graph");
		return opiGraphService.generateGraphObi().toString();
	}

	@GetMapping({"/sbc/", "/sbc"})
	@ApiOperation(value = "Show SBC graph")
	public String getSbcGraph() {
		LOGGER.info("Show SBC graph");
		return opiGraphService.generateGraphSbc().toString();
	}

}
