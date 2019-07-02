package br.edu.opi.manager.office_io.controllers;

import br.edu.opi.manager.office_io.dtos.CompetitorAwardedIO;
import br.edu.opi.manager.office_io.dtos.CompetitorAwardedInput;
import br.edu.opi.manager.office_io.services.WordGenerateService;
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

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(RestConstants.OFFICE_URI)
@Api(tags = "Office")
@CrossOrigin
public class WordParserController {

	private CompetitorAwardedIO competitorsAwardedIO;
	private WordGenerateService wordGenerateService;

	@Autowired
	public WordParserController(
			CompetitorAwardedIO competitorsAwardedIO,
			WordGenerateService wordGenerateService) {
		this.competitorsAwardedIO = competitorsAwardedIO;
		this.wordGenerateService = wordGenerateService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_COMPETITOR + "')")
	@GetMapping({"/competitors/{id}/awards/download/", "/competitors/{id}/awards/download"})
	@ApiOperation(value = "Download awards")
	public ResponseEntity<Resource> downloadAwardDocx(
			@PathVariable("id") Long competidorId,
			@RequestParam(required = false, value = "award", defaultValue = "Participação") String award) { // TODO: remove
		Resource resource = wordGenerateService.downloadCertified(competidorId);
		String contentType = "application/octet-stream";
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "certificado.docx" + "\"")
				.body(resource);
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_COMPETITOR + "')")
	@PostMapping({"/competitors/awards/download/", "/competitors/awards/download"})
	@ApiOperation(value = "Download all awards")
	public ResponseEntity<Resource> downloadAwardDocx(
			@RequestBody List<CompetitorAwardedInput> input) {
		Set<Long> awardSet = competitorsAwardedIO.toAwardedSet(input);
		Resource resource = wordGenerateService.downloadCertifieds(awardSet);
		String contentType = "application/octet-stream";
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "certificados.docx" + "\"")
				.body(resource);
	}

}
