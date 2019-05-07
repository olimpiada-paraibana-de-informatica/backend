package br.edu.opi.manager.project_patterns.dto.history;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class AuditingOutput {

	@ApiModelProperty(example = "2019-01-01")
	protected LocalDate createdAt;

	@ApiModelProperty(example = "2019-01-01")
	protected LocalDate updatedAt;

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

}
