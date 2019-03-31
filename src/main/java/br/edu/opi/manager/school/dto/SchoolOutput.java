package br.edu.opi.manager.school.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CNPJ;

public class SchoolOutput {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Colégio de Teste")
	private String name;

	@ApiModelProperty(example = "Jose")
	private String delegateName;

	@ApiModelProperty(example = "+55 55 55555-5555")
	private String delegatePhone;

	@CNPJ
	@ApiModelProperty(example = "11728649000140")
	private String cnpj;

	@ApiModelProperty(example = "+55 55 5555-5555")
	private String phone;

	@ApiModelProperty(example = "true")
	private boolean enabled;

	public SchoolOutput() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDelegateName() {
		return delegateName;
	}

	public void setDelegateName(String delegateName) {
		this.delegateName = delegateName;
	}

	public String getDelegatePhone() {
		return delegatePhone;
	}

	public void setDelegatePhone(String delegatePhone) {
		this.delegatePhone = delegatePhone;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
