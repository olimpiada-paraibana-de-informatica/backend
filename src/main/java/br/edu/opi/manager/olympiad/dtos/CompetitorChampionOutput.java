package br.edu.opi.manager.olympiad.dtos;

import io.swagger.annotations.ApiModelProperty;

public class CompetitorChampionOutput {

	@ApiModelProperty
	private Integer studentId = 1; // TODO: change to personId

	@ApiModelProperty
	private String name;

	@ApiModelProperty
	private String school;

	@ApiModelProperty
	private String cityName;

	public CompetitorChampionOutput() {
	}

	public CompetitorChampionOutput(Integer studentId, String name, String school, String cityName) {
		this.studentId = studentId;
		this.name = name;
		this.school = school;
		this.cityName = cityName;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
