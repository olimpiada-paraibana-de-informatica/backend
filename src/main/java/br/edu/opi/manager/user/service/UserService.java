package br.edu.opi.manager.user.service;

import br.edu.opi.manager.user.model.UserFactory;
import br.edu.opi.manager.user.repository.UserRepository;
import br.edu.opi.manager.project_patterns.exceptions.NotFoundRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.UpdateConflictRuntimeException;
import br.edu.opi.manager.project_patterns.models.user.Profile;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.security.exception.SamePasswordRuntimeException;
import br.edu.opi.manager.security.Payload;
import br.edu.opi.manager.security.SecurityFilter;
import br.edu.opi.manager.security.TokenSecurityService;
import br.edu.opi.manager.user.model.UserModel;
import br.edu.opi.manager.utils.CryptoUtil;
import br.edu.opi.manager.utils.ErrorMessagesConstants;
import br.edu.opi.manager.utils.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Business logic layer to Simple User.
 */
@Service
public class UserService extends GenericService<Long, UserModel, UserRepository> {

	private CryptoUtil cryptoUtil;

	private SecurityFilter securityFilter;

	private TokenSecurityService tokenSecurityService;

	@Autowired
	public UserService(
			CryptoUtil cryptoUtil,
			SecurityFilter tokenAuthenticationService,
			TokenSecurityService tokenSecurityService) {
		this.cryptoUtil = cryptoUtil;
		this.securityFilter = tokenAuthenticationService;
		this.tokenSecurityService = tokenSecurityService;
	}

	public UserModel login(String login, String credentials) {
		UserModel user = repository.findByUsername(login);
		if (user != null && cryptoUtil.matches(credentials, user.getPassword())) {
			user.setPassword(null);
			return user;
		}
		return null;
	}

	public void logout(String token) {
//		blackListService.blockToken(token);
	}

	public void logoutAll(String username) {
//		blackListService.blockUser(username);
	}

	public String generateToken(UserModel user) {
		Payload payload = UserFactory.createPayload(user);
		return tokenSecurityService.generateToken(payload);
	}

	public UserModel findByUsernameWithPassword(String username) {
		return repository.findByUsername(username);
	}

	public UserModel findByUsername(String username) {
		return repository.findByUsername(username);
	}

	public Set<UserModel> findByUsernameContaining(String username) {
		return repository.findByUsernameContaining(username);
	}

	@Override
	public UserModel update(Long id, UserModel model) {
		UserModel user = repository.findById(id).orElseThrow(NotFoundRuntimeException::new);
		if (!user.getId().equals(id)) {
			throw new IllegalArgumentException(ErrorMessagesConstants.USER_IDS_DO_NOT_MATCH);
		}
		try {
			model.setId(id);
			model.setProfile(user.getProfile());
			model.setLocked(!user.isAccountNonLocked());
			model.setEnabled(user.isEnabled());
			model.setNeedChangePassword(user.isNeedChangePassword());
			//UserModel userDB = repository.save(model);
//			blackListService.blockUser(userDB.getUsername());
			return model;
		} catch (Exception e) {
			throw new UpdateConflictRuntimeException(e.getLocalizedMessage());
		}
	}

	public void updateLocked(Long id, boolean status) {
		UserModel user = repository.findById(id).orElseThrow(NotFoundRuntimeException::new);
		user.setLocked(status);
		repository.save(user);
//		blackListService.blockUser(user.getUsername());
	}

	public void updateEnable(Long id, boolean status) {
		UserModel user = repository.findById(id).orElseThrow(NotFoundRuntimeException::new);
		user.setEnabled(status);
		repository.save(user);
//		blackListService.blockUser(user.getUsername());
	}

	public UserModel updatePassword(Long id) {
		UserModel userSaved = repository.findById(id).orElseThrow(NotFoundRuntimeException::new);
		userSaved.setNeedChangePassword(true);
		return updatePassword(userSaved, RestConstants.DEFAULT_PASSWORD);
	}

	public UserModel updatePassword(String username, String password) {
		UserModel userSaved = repository.findByUsername(username);
		userSaved.setNeedChangePassword(false);
		return updatePassword(userSaved, password);
	}

	private UserModel updatePassword(UserModel userSaved, String password) {
		if (userSaved == null) {
			throw new NotFoundRuntimeException();
		}
		if (cryptoUtil.matches(password, userSaved.getPassword())) {
			throw new SamePasswordRuntimeException();
		}
		userSaved.setPassword(password);
		return repository.save(userSaved);
	}

	public void updateProfile(Long id, Long profileId) {
		UserModel user = repository.findById(id).orElseThrow(NotFoundRuntimeException::new);
		user.setProfile(new Profile(profileId));
		repository.save(user);
//		blackListService.blockUser(user.getUsername());
	}

	public List<UserModel> index(Profile profile) {
		return repository.findAllByProfileId(profile.getId());
	}

	public List<UserModel> index(String value) {
		if (value != null && !value.isEmpty()) {
			return repository.findFirst10ByUsernameContainingIgnoreCase(value, DEFAULT_SORT);
		}
		return repository.findAll(DEFAULT_SORT);
	}

	@Override
	public void validateBeforeCreate(UserModel model) {
	}

	@Override
	public void validateBeforeUpdate(UserModel model) {
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}

}
