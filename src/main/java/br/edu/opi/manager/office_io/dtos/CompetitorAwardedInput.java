package br.edu.opi.manager.office_io.dtos;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CompetitorAwardedInput {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long competitorId;

	public CompetitorAwardedInput() {
	}

	public Long getCompetitorId() {
		return competitorId;
	}

	public void setCompetitorId(Long competitorId) {
		this.competitorId = competitorId;
	}

}
