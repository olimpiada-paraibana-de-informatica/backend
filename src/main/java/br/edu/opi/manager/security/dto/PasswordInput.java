package br.edu.opi.manager.security.dto;

import br.edu.opi.manager.project_patterns.models.user.ValidPassword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PasswordInput")
public class PasswordInput {

	@ApiModelProperty(example = "1P@ssw0rd", required = true)
	@ValidPassword
	private String password;

	public PasswordInput(String password) {
		this.password = password;
	}

	public PasswordInput() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
