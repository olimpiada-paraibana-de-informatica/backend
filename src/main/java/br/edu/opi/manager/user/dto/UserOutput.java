package br.edu.opi.manager.user.dto;

import br.edu.opi.manager.conventions.dto.user.ProfileUserOutput;
import br.edu.opi.manager.history.dto.AuditingOutput;
import br.edu.opi.manager.*;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserOutput extends AuditingOutput {

	@ApiModelProperty(example = "1", required = true)
	@NotEmpty
	private Long id;

	@ApiModelProperty(example = "administrador@email.com", required = true)
	@NotEmpty
	@Email
	private String username;

	@ApiModelProperty(example = "Maria", required = true)
	private String firstName;

	@ApiModelProperty(example = "Silva", required = true)
	private String lastName;

	@ApiModelProperty(required = true)
	private ProfileUserOutput profile;

	@ApiModelProperty(example = "950.213.940-29", required = true)
	private String cpf;

	@ApiModelProperty(example = "true", required = true)
	private boolean needChangePassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ProfileUserOutput getProfile() {
		return profile;
	}

	public void setProfile(ProfileUserOutput profile) {
		this.profile = profile;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isNeedChangePassword() {
		return needChangePassword;
	}

	public void setNeedChangePassword(boolean needChangePassword) {
		this.needChangePassword = needChangePassword;
	}

}
