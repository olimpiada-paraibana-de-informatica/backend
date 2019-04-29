package br.edu.opi.manager.competitor.dtos;

import br.edu.opi.manager.olympiad.models.OpiCategory;
import br.edu.opi.manager.person.models.Genre;
import br.edu.opi.manager.school.models.Grade;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class CompetitorOutput {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Fulano de Tal")
	private String name;

	@ApiModelProperty(example = "2019-01-21")
	private LocalDate dateBirth;

	@ApiModelProperty
	private Genre genre;

	@ApiModelProperty
	private Grade grade;

	@ApiModelProperty
	private OpiCategory category;

	@ApiModelProperty(example = "1")
	private Long studentId;

	public CompetitorOutput() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public OpiCategory getCategory() {
		return category;
	}

	public void setCategory(OpiCategory category) {
		this.category = category;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

}
