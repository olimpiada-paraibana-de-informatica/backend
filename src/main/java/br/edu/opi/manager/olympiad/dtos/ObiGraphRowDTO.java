package br.edu.opi.manager.olympiad.dtos;

import io.swagger.annotations.ApiModelProperty;

public class ObiGraphRowDTO {

	@ApiModelProperty(example = "2018")
	private int year;

	@ApiModelProperty(example = "2")
	private int total;

	@ApiModelProperty(example = "Ouro")
	private String award;

	@ApiModelProperty(example = "Iniciação 1")
	private String obiCategory;

	public ObiGraphRowDTO() {
	}

	public ObiGraphRowDTO(int year, int total, String award, String obiCategory) {
		this.year = year;
		this.total = total;
		this.award = award;
		this.obiCategory = obiCategory;
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

	public String getObiCategory() {
		return obiCategory;
	}

	public void setObiCategory(String obiCategory) {
		this.obiCategory = obiCategory;
	}

	@Override
	public String toString() {
		return String.format("{\"year\": %d, \"total\": %d, \"award\": \"%s\", \"category\": \"%s\"},",
				year, total, award, obiCategory);
	}

}
