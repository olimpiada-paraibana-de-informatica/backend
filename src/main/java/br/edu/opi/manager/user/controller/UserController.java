package br.edu.opi.manager.user.controller;

import br.edu.opi.manager.project_patterns.dto.AppControllerBase;
import br.edu.opi.manager.security.SecurityUtils;
import br.edu.opi.manager.security.dto.PasswordInput;
import br.edu.opi.manager.user.dto.UserIO;
import br.edu.opi.manager.user.dto.UserInput;
import br.edu.opi.manager.user.dto.UserOutput;
import br.edu.opi.manager.user.dto.UserProfileInput;
import br.edu.opi.manager.user.service.UserService;
import br.edu.opi.manager.utils.RestConstants;
import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.security.dto.AccountCredentials;
import br.edu.opi.manager.security.TokenSecurityService;
import br.edu.opi.manager.user.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.lang.reflect.Type;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(RestConstants.USER_URI)
@Api(tags = "Users")
@CrossOrigin
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getSimpleName());

	private UserService userService;

	private AppControllerBase appControllerBase;

	private UserIO userIO;

	@Autowired
	public UserController(
			UserService userService,
			AppControllerBase appControllerBase,
			UserIO userIO) {
		this.userService = userService;
		this.appControllerBase = appControllerBase;
		this.userIO = userIO;
	}

	@PostMapping({"/login/", "/login"})
	@ApiOperation(value = "Login an User")
	public ResponseEntity<UserOutput> loginUser(@RequestBody @Valid AccountCredentials accountCredentials) {
		LOGGER.info("trying logging " + accountCredentials.getUsername());
		UserModel user = userService.login(
				accountCredentials.getUsername(),
				accountCredentials.getPassword());
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		LOGGER.info("user " + accountCredentials.getUsername() + " logged");
		HttpHeaders headers = new HttpHeaders();
		headers.add(SecurityUtils.TOKEN_HEADER, userService.generateToken(user));
		UserOutput userOutput = appControllerBase.mapTo(user, UserOutput.class);
		return ResponseEntity.ok().headers(headers).body(userOutput);
	}

	@PostMapping({"/logout/", "/logout"})
	@ApiOperation(value = "Logout an User by token")
	public ResponseEntity<?> logoutUser(
			Principal principal,
			@RequestHeader(value = TokenSecurityService.HEADER) String token) {
		LOGGER.info("trying logout user " + principal.getName());
		userService.logout(token);
		LOGGER.info("logout " + principal.getName());
		return ResponseEntity.noContent().build();
	}

	@PostMapping({"/logout/all/", "/logout/all"})
	@ApiOperation(value = "Logout an User by token")
	public ResponseEntity<?> logoutUsersFromAll(Principal principal) {
		LOGGER.info("trying logout user " + principal.getName() + " from all machines");
		userService.logoutAll(principal.getName());
		LOGGER.info("logout " + principal.getName() + " from all machines");
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_USER + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Create an User", notes = "Also returns a link to retrieve the saved user in the location header")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserInput user) {
		UserModel userModel = userIO.mapTo(user);
		LOGGER.info("trying create new user " + userModel.getUsername());
		UserModel savedUser = userService.create(userModel);
		// @formatter:off
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		LOGGER.info("user " + userModel.getUsername() + " create at " + location);
		return ResponseEntity.created(location).build();
		// @formatter:on
	}

	@PreAuthorize("hasAuthority('" + Privilege.INDEX_USER + "')")
	@ApiOperation(value = "Get All Users")
	@GetMapping({"/", ""})
	// @formatter:off
	public ResponseEntity<?> indexUsers(
			@RequestParam(required = false, name = "page") Integer page,
			@RequestParam(required = false, name = "size") Integer size,
			@RequestParam(required = false, name = "value") String value) {
		LOGGER.info("index users");
		Type type = new TypeToken<List<UserOutput>>() {}.getType();
		if (StringUtils.isEmpty(value)) {
			List<UserOutput> result = appControllerBase.toList(userService.index(value), type);
			return ResponseEntity.ok(result);
		} else if (page == null && size == null) {
			List<UserOutput> result = appControllerBase.toList(userService.index(), type);
			return ResponseEntity.ok(result);
		} else {
			Page<UserOutput> result = appControllerBase.toPage(userService.index(page, size), type);
			return ResponseEntity.ok(result);
		}
	}
	// @formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_USER + "')")
	@ApiOperation(value = "Get an only User")
	@GetMapping({"/{id}/", "/{id}"})
	public UserOutput showUser(@PathVariable("id") Long id) {
		LOGGER.info("show user");
		return appControllerBase.mapTo(userService.show(id), UserOutput.class);
	}

	@PreAuthorize("hasAuthority('" + Privilege.UPDATE_USER + "')")
	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Updates an User")
	public ResponseEntity<?> updateUser(
			// @formatter:off
			@Min(value = 1) @PathVariable("id") Long id,
			@Valid @RequestBody UserInput user) {
		UserModel userModel = userIO.mapTo(user);
		LOGGER.info("trying update user " + userModel.getUsername());
		userService.update(id, userModel);
		LOGGER.info("user " + userModel.getUsername() + " updated");
		return ResponseEntity.noContent().build();
	}
	// @formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.DELETE_USER + "')")
	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delete an User")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		LOGGER.info("trying deleting user " + id);
		userService.delete(id);
		LOGGER.info("user " + id + " deleted");
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_USER + "')")
	@ApiOperation(value = "Get an User from the token")
	@GetMapping({"/data/", "/data"})
	public UserOutput showUser(Principal principal) {
		LOGGER.info("show user data");
		return appControllerBase.mapTo(userService.findByUsername(principal.getName()), UserOutput.class);
	}

	@PatchMapping({"/password/", "/password"})
	@ApiOperation(value = "Updates the password of an User from token")
	public ResponseEntity<?> updateUserPassword(
			// @formatter:off
			@Valid @RequestBody PasswordInput passwordInput, Principal principal) {
		LOGGER.info("trying update user password " + principal.getName());
		userService.updatePassword(principal.getName(), passwordInput.getPassword());
		LOGGER.info("user " + principal.getName() + " password updated");
		return ResponseEntity.noContent().build();
	}
	// @formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.RESET_USER_PASSWORD + "')")
	@PatchMapping({"/{id}/reset/password/", "/{id}/reset/password"})
	@ApiOperation(value = "Reset user password")
	public ResponseEntity<?> resetUserPassword(
			// @formatter:off
			@Min(value = 1) @PathVariable("id") Long id) {
		LOGGER.info("trying reset user password " + id);
		UserModel userChanged = userService.updatePassword(id);
		LOGGER.info("user " + userChanged.getUsername() + " password reseted");
		return ResponseEntity.noContent().build();
	}
	// @formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.CHANGE_USER_PROFILE + "')")
	@PatchMapping({"/profile/", "/profile"})
	@ApiOperation(value = "Updates the profile of an User")
	public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileInput input) {
		LOGGER.info("trying update user profile");
		userService.updateProfile(input.getUserId(), input.getProfileId());
		LOGGER.info("user profile updated");
		return ResponseEntity.noContent().build();
	}

}
