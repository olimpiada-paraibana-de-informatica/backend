package br.edu.opi.manager.user.dto;

import br.edu.opi.manager.conventions.models.ValidPassword;
import br.edu.opi.manager.utils.ErrorMessagesConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ApiModel(value = "UserInput")
public class UserInput {

	@ApiModelProperty(example = "user@user.com", required = true)
	@NotEmpty
	@Email(message = ErrorMessagesConstants.EMAIL_INVALID)
	private String username;

	@ApiModelProperty(example = "P@ssw0rd", required = true)
	@NotEmpty
	@ValidPassword(message = ErrorMessagesConstants.PASSWORD_INVALID)
	private String password;

	@ApiModelProperty(example = "User", required = true)
	private String firstName;

	@ApiModelProperty(example = "LastName", required = true)
	private String lastName;

	@CPF(message = ErrorMessagesConstants.CPF_INVALID)
	@ApiModelProperty(example = "950.213.940-29", required = true)
	private String cpf;

	@ApiModelProperty(example = "1", required = true)
	private Long profileId;

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
