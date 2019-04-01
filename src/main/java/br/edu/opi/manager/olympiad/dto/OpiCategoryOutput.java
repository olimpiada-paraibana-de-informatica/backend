package br.edu.opi.manager.olympiad.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "opiCategoryOutput")
public class OpiCategoryOutput {

	@ApiModelProperty(example = "Iniciação_1")
	private String key;

	@ApiModelProperty(example = "Iniciação 1")
	private String name;

	public OpiCategoryOutput() {
	}

	public OpiCategoryOutput(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
