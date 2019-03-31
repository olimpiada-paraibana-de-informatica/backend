package br.edu.opi.manager.delegate.dto;

import br.edu.opi.manager.project_patterns.models.user.ValidPassword;
import br.edu.opi.manager.utils.ErrorMessagesConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ApiModel(value = "delegateInput")
public class DelegateInput {

	@ApiModelProperty(example = "Jose")
	private String name;

	@ApiModelProperty(example = "user@user.com", required = true)
	@NotEmpty
	@Email(message = ErrorMessagesConstants.EMAIL_INVALID)
	private String username;

	@ApiModelProperty(example = "P@ssw0rd")
	@NotEmpty
	@ValidPassword(message = ErrorMessagesConstants.PASSWORD_INVALID)
	private String password;

	@CPF(message = ErrorMessagesConstants.CPF_INVALID)
	@ApiModelProperty(example = "950.213.940-29")
	private String cpf;

	@ApiModelProperty(example = "83-88888888")
	private String phone;

	@ApiModelProperty(example = "1")
	private Long profileId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

}
