package br.edu.opi.manager.office_io.controllers;

import br.edu.opi.manager.office_io.services.WordGenerateService;
import br.edu.opi.manager.olympiad.models.OpiAward;
import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestConstants.OFFICE_URI)
@Api(tags = "Office")
@CrossOrigin
public class WordParserController {

	private WordGenerateService wordGenerateService;

	@Autowired
	public WordParserController(WordGenerateService wordGenerateService) {
		this.wordGenerateService = wordGenerateService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_COMPETITOR + "')")
	@GetMapping({"/competitors/{id}/awards/download/", "/competitors/{id}/awards/download"})
	@ApiOperation(value = "Awards download")
	public ResponseEntity<Resource> downloadCompetitorSheet(
			@PathVariable("id") Long competidorId,
			@RequestParam("award") String award) {
		Resource resource = wordGenerateService.downloadCertified(OpiAward.from(award).getName(), competidorId);
		String contentType = "application/octet-stream";
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
