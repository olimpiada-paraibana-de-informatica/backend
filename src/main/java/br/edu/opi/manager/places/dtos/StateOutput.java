package br.edu.opi.manager.places.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "stateOutput")
public class StateOutput {

	@ApiModelProperty(example = "25")
	private Long cboCode;

	@ApiModelProperty(example = "Paraíba")
	private String name;

	public StateOutput() {
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

}
