package br.edu.opi.manager.school.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;

@ApiModel(value = "schoolInput")
public class SchoolInput {

	@ApiModelProperty(example = "Colégio de Teste", required = true)
	private String name;

	@Email
	@ApiModelProperty(example = "delegate@email.com", required = true)
	private String delegateUsername;

	@ApiModelProperty(example = "1")
	private Long delegateId;

	@CNPJ
	@ApiModelProperty(example = "11728649000140")
	private String cnpj;

	@ApiModelProperty(example = "+55 55 5555-5555", required = true)
	private String phone;

	public SchoolInput() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDelegateUsername() {
		return delegateUsername;
	}

	public void setDelegateUsername(String delegateUsername) {
		this.delegateUsername = delegateUsername;
	}

	public Long getDelegateId() {
		return delegateId;
	}

	public void setDelegateId(Long delegateId) {
		this.delegateId = delegateId;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
