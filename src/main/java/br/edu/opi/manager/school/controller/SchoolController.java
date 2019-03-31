package br.edu.opi.manager.school.controller;

import br.edu.opi.manager.conventions.dto.AppControllerBase;
import br.edu.opi.manager.conventions.models.Privilege;
import br.edu.opi.manager.delegate.dto.DelegateInput;
import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.delegate.service.DelegateService;
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

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(RestConstants.DELEGATE_URI)
@Api(tags = "Delegate")
@CrossOrigin
public class SchoolController {

	private static final Logger LOGGER = LoggerFactory.getLogger(br.edu.opi.manager.school.controller.SchoolController.class.getSimpleName());

	@Autowired
	AppControllerBase appControllerBase;

	@Autowired
	DelegateService delegateService;

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_USER + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Create a Delegate", notes = "Also returns a link to retrieve the saved Delegate in the location header")
	public ResponseEntity<Object> create(@Valid @RequestBody DelegateInput delegateInput) {
		Delegate delegate = appControllerBase.mapTo(delegateInput, Delegate.class);
		LOGGER.info("trying create new Delegate " + delegate.getId());
		Delegate savedDelegate = delegateService.create(delegate);
		// @formatter:off
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedDelegate.getId())
				.toUri();
		LOGGER.info("Delegate " + delegate.getId() + " create at " + location);
		return ResponseEntity.created(location).build();
		// @formatter:on
	}

//	@PreAuthorize("hasAuthority('" + Privilege.SHOW_USER + "')")
//		@ApiOperation(value = "Get an only User")
//		@GetMapping({"/{id}/", "/{id}"})
//		public UserOutput showUser(@PathVariable("id") Long id) {
//			LOGGER.info("show user");
//			return appControllerBase.mapTo(userService.show(id), UserOutput.class);
//		}
//
//		@PreAuthorize("hasAuthority('" + Privilege.UPDATE_USER + "')")
//		@PutMapping({"/{id}/", "/{id}"})
//		@ApiOperation(value = "Updates an User")
//		public ResponseEntity<?> updateUser(
//				// @formatter:off
//				@Min(value = 1) @PathVariable("id") Long id,
//				@Valid @RequestBody UserInput user) {
//			UserModel userModel = userIO.mapTo(user);
//			LOGGER.info("trying update user " + userModel.getUsername());
//			userService.update(id, userModel);
//			LOGGER.info("user " + userModel.getUsername() + " updated");
//			return ResponseEntity.noContent().build();
//		}
//		// @formatter:on
//
//		@PreAuthorize("hasAuthority('" + Privilege.DELETE_USER + "')")
//		@DeleteMapping({"/{id}/", "/{id}"})
//		@ApiOperation(value = "Delete an User")
//		public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
//			LOGGER.info("trying deleting user " + id);
//			userService.delete(id);
//			LOGGER.info("user " + id + " deleted");
//			return ResponseEntity.ok().build();
//		}
//
//		@PreAuthorize("hasAuthority('" + Privilege.SHOW_USER + "')")
//		@ApiOperation(value = "Get an User from the token")
//		@GetMapping({"/data/", "/data"})
//		public UserOutput showUser(Principal principal) {
//			LOGGER.info("show user data");
//			return appControllerBase.mapTo(userService.findByUsername(principal.getName()), UserOutput.class);
//		}
//
//		@PatchMapping({"/password/", "/password"})
//		@ApiOperation(value = "Updates the password of an User from token")
//		public ResponseEntity<?> updateUserPassword(
//				// @formatter:off
//				@Valid @RequestBody PasswordInput passwordInput, Principal principal) {
//			LOGGER.info("trying update user password " + principal.getName());
//			userService.updatePassword(principal.getName(), passwordInput.getPassword());
//			LOGGER.info("user " + principal.getName() + " password updated");
//			return ResponseEntity.noContent().build();
//		}
//		// @formatter:on
//
//		@PreAuthorize("hasAuthority('" + Privilege.RESET_USER_PASSWORD + "')")
//		@PatchMapping({"/{id}/reset/password/", "/{id}/reset/password"})
//		@ApiOperation(value = "Reset user password")
//		public ResponseEntity<?> resetUserPassword(
//				// @formatter:off
//				@Min(value = 1) @PathVariable("id") Long id) {
//			LOGGER.info("trying reset user password " + id);
//			UserModel userChanged = userService.updatePassword(id);
//			LOGGER.info("user " + userChanged.getUsername() + " password reseted");
//			return ResponseEntity.noContent().build();
//		}
//		// @formatter:on
//
//		@PreAuthorize("hasAuthority('" + Privilege.CHANGE_USER_PROFILE + "')")
//		@PatchMapping({"/profile/", "/profile"})
//		@ApiOperation(value = "Updates the profile of an User")
//		public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileInput input) {
//			LOGGER.info("trying update user profile");
//			userService.updateProfile(input.getUserId(), input.getProfileId());
//			LOGGER.info("user profile updated");
//			return ResponseEntity.noContent().build();
//		}
//
//	}


}