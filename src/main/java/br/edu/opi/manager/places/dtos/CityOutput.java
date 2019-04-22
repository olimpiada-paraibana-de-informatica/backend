package br.edu.opi.manager.places.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "cityOutput")
public class CityOutput {

	@ApiModelProperty(example = "2504009")
	private Long cboCode;

	@ApiModelProperty(example = "Campina Grande")
	private String name;

	@ApiModelProperty(example = "PB")
	private String stateAbbreviation;

	public CityOutput() {
	}

	public CityOutput(Long cboCode, String name, String stateAbbreviation) {
		this.cboCode = cboCode;
		this.name = name;
		this.stateAbbreviation = stateAbbreviation;
	}

	public Long getCboCode() {
		return cboCode;
	}

	public void setCboCode(Long cboCode) {
		this.cboCode = cboCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStateAbbreviation() {
		return stateAbbreviation;
	}

	public void setStateAbbreviation(String stateAbbreviation) {
		this.stateAbbreviation = stateAbbreviation;
	}

}
