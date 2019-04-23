package br.edu.opi.manager.competitor.dtos;

import br.edu.opi.manager.olympiad.models.OpiCategory;
import br.edu.opi.manager.school.models.Grade;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class CompetitorOutput {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long studentId;

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Grade studentGrade;

	@ApiModelProperty(example = "", required = true)
	@NotNull
	private OpiCategory studentCategory;


	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Grade getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(Grade studentGrade) {
		this.studentGrade = studentGrade;
	}

	public OpiCategory getStudentCategory() {
		return studentCategory;
	}

	public void setStudentCategory(OpiCategory studentCategory) {
		this.studentCategory = studentCategory;
	}
}
