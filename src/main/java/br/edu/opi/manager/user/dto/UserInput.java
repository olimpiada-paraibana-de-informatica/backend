package br.edu.opi.manager.user.dto;

import br.edu.opi.manager.project_patterns.models.user.ValidPassword;
import br.edu.opi.manager.utils.ErrorMessagesConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ApiModel(value = "UserInput")
public class UserInput {

	@ApiModelProperty(example = "administrador@email.com", required = true)
	@NotEmpty
	@Email(message = ErrorMessagesConstants.EMAIL_INVALID)
	private String email;

	@ApiModelProperty(example = "P@ssw0rd", required = true)
	@NotEmpty
	@ValidPassword(message = ErrorMessagesConstants.PASSWORD_INVALID)
	private String password;

	@ApiModelProperty(example = "User", required = true)
	private String name;

	@CPF(message = ErrorMessagesConstants.CPF_INVALID)
	@ApiModelProperty(example = "950.213.940-29")
	private String cpf;

	@ApiModelProperty(example = "1", required = true)
	private Long profileId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

}
