package br.edu.opi.manager.user.models;

import br.edu.opi.manager.project_patterns.models.user.Profile;
import br.edu.opi.manager.security.Payload;

/**
 * @see Profile
 */
public class UserFactory {

	public UserFactory() {
	}

	// Security

	public static Payload createPayload(UserModel user) {
		return new Payload(
				user.getUsername(),
				user.getProfile().getPrivileges(),
				user.isEnabled(),
				!user.isAccountNonLocked(),
				user.isNeedChangePassword());
	}

	// User

	public static UserModel createUserObject(
			String username,
			String password,
			String name,
			String cpf,
			Profile profile) {
		UserModel user = new UserModel(username, password, name, cpf, profile);
		user.setEnabled(true);
		user.setLocked(false);
		return user;
	}

}
