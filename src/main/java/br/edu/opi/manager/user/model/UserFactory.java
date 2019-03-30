package br.edu.opi.manager.user.model;

import br.edu.opi.manager.conventions.models.Profile;
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
			String firstName,
			String lastName,
			String cpf,
			Profile profile) {
		UserModel user = new UserModel(username, password, firstName, lastName, cpf, profile);
		user.setEnabled(true);
		user.setLocked(false);
		return user;
	}

}
