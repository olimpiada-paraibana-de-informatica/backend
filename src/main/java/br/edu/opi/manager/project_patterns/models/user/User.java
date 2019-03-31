package br.edu.opi.manager.project_patterns.models.user;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface to Simple UserModel.
 */
public interface User extends UserDetails {

	public String getUsername();

	public void setUsername(String email);

	public String getPassword();

	public void setPassword(String password);

	public String getCpf();

	public void setCpf(String cpf);

	public Profile getProfile();

	public void setProfile(Profile profile);

	public boolean isNeedChangePassword();

	public void setNeedChangePassword(boolean status);

	public void setLocked(boolean locked);

	public void setEnabled(boolean enabled);

}
