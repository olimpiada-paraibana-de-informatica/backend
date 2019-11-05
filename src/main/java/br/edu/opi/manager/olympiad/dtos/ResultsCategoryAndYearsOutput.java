package br.edu.opi.manager.olympiad.dtos;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ResultsCategoryAndYearsOutput {

	@ApiModelProperty(example = "iniciacao1")
	private String type;

	@ApiModelProperty(example = "[2015, 2016, 2017, 2018, 2019]")
	private List<Integer> year;

	public ResultsCategoryAndYearsOutput() {
	}

	public ResultsCategoryAndYearsOutput(String type, List<Integer> year) {
		this.type = type;
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getYear() {
		return year;
	}

	public void setYear(List<Integer> year) {
		this.year = year;
	}

}
