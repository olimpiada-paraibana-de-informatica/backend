package br.edu.opi.manager.olympiad.dtos;

import io.swagger.annotations.ApiModelProperty;

public class InternationalChampionOutput {

	@ApiModelProperty
	private Long id;

	@ApiModelProperty
	private int year;

	@ApiModelProperty
	private String nomes;

	@ApiModelProperty
	private String school;

	@ApiModelProperty
	private String details;

	public InternationalChampionOutput() {
	}

	public InternationalChampionOutput(Long id, int year, String nomes, String school, String details) {
		this.id = id;
		this.year = year;
		this.nomes = nomes;
		this.school = school;
		this.details = details;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getNomes() {
		return nomes;
	}

	public void setNomes(String nomes) {
		this.nomes = nomes;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
