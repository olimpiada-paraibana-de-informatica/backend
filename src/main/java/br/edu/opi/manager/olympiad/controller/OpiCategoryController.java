package br.edu.opi.manager.olympiad.controller;

import br.edu.opi.manager.olympiad.dto.OpiCategoryOutput;
import br.edu.opi.manager.olympiad.service.OpiCategoryService;
import br.edu.opi.manager.project_patterns.dto.AppControllerBase;
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
@RequestMapping(RestConstants.OPI_CATEGORY_URI)
@Api(tags = "Category")
@CrossOrigin
public class OpiCategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpiCategoryController.class.getSimpleName());

	private OpiCategoryService opiCategoryService;

	private AppControllerBase appControllerBase;

	@Autowired
	public OpiCategoryController(
			OpiCategoryService opiCategoryService,
			AppControllerBase appControllerBase) {
		this.opiCategoryService = opiCategoryService;
		this.appControllerBase = appControllerBase;
	}

	@GetMapping({"/", ""})
	@ApiOperation(value = "Index Opi Categories")
	public List<OpiCategoryOutput> index() {
		LOGGER.info("index opi categories");
		Type dest = new TypeToken<List<OpiCategoryOutput>>() {}.getType();
		return appControllerBase.toList(opiCategoryService.index(), dest);
	}

}
