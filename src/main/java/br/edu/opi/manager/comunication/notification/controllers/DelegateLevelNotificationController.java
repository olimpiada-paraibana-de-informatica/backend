package br.edu.opi.manager.comunication.notification.controllers;

import br.edu.opi.manager.comunication.notification.models.DelegateLevelNotification;
import br.edu.opi.manager.comunication.notification.services.DelegateLevelNotificationService;
import br.edu.opi.manager.olympiad.models.OpiLevels;
import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.school.dtos.SchoolIO;
import br.edu.opi.manager.school.dtos.SchoolOutput;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(RestConstants.DELEGATE_LEVELS_NOTICE_URI)
@Api(tags = "Notices")
@CrossOrigin
// TODO: rethink
public class DelegateLevelNotificationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DelegateLevelNotificationController.class.getSimpleName());

	private DelegateLevelNotificationService delegateLevelNotificationService;

	private SchoolIO schoolIO;

	@Autowired
	public DelegateLevelNotificationController(
			DelegateLevelNotificationService delegateLevelNotificationService,
			SchoolIO schoolIO) {
		this.delegateLevelNotificationService = delegateLevelNotificationService;
		this.schoolIO = schoolIO;
	}

	@PreAuthorize("hasAuthority('" + Privilege.LEVEL_NOTICE + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Delegate advises that he / she has filled out all the students' scores")
	public ResponseEntity<Object> create(@RequestBody OpiLevels level, Principal principal) {
		LOGGER.info(principal.getName() + " trying create new delegate level notification");
		// TODO: refactor principal.getName with loggedUserId variable context
		DelegateLevelNotification notification = delegateLevelNotificationService.create(level, principal.getName());
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(notification.getId())
				.toUri();
		LOGGER.info("delegate level notification created");
		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasAuthority('" + Privilege.LEVEL_NOTICE + "')")
	@GetMapping({"/", ""})
	@ApiOperation(value = "Index schools who put students scores")
	public List<SchoolOutput> index(@RequestParam Integer year) {
		LOGGER.info("delegates who put students scores");
		return schoolIO.toList(delegateLevelNotificationService.index(year));
	}

}
