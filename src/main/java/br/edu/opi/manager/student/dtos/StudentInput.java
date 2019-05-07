package br.edu.opi.manager.student.dtos;

import br.edu.opi.manager.student.models.Genre;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class StudentInput {

	@ApiModelProperty(example = "José da Silva", required = true)
	@NotEmpty
	private String name;

	@ApiModelProperty(example = "1998-03-25", required = true)
	@NotNull
	private LocalDate dateBirth;

	@ApiModelProperty(required = true)
	@NotNull
	private Genre genre;

	@ApiModelProperty
	private Long schoolId;

	public StudentInput() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

}
