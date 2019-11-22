package br.edu.opi.manager.olympiad.dtos;

import io.swagger.annotations.ApiModelProperty;

public class SbcGraphRowDTO {

	@ApiModelProperty(example = "2018")
	private int year;

	@ApiModelProperty(example = "2")
	private int total;

	@ApiModelProperty(example = "Ouro")
	private String award;

	public SbcGraphRowDTO() {
	}

	public SbcGraphRowDTO(int year, int total, String award) {
		this.year = year;
		this.total = total;
		this.award = award;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	@Override
	public String toString() {
		return String.format("{\"year\": %d, \"total\": %d, \"award\": \"%s\"},",
				year, total, award);
	}

}
