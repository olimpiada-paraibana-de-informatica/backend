package br.edu.opi.manager.office_io.dtos;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CompetitorAwardedInput {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long competitorId;

	@ApiModelProperty(example = "Ouro", required = true)
	@NotBlank
	private String award;

	public CompetitorAwardedInput() {
	}

	public Long getCompetitorId() {
		return competitorId;
	}

	public void setCompetitorId(Long competitorId) {
		this.competitorId = competitorId;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

}
