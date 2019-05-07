package br.edu.opi.manager.comunication.email.dtos;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;

public class EmailInput {

	@ApiModelProperty(example = "email@email.com")
	@Email
	private String name;

	public EmailInput() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
