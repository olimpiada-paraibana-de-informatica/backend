package br.edu.opi.manager.competitor.dtos;

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
	private String genre;

	@ApiModelProperty
	private String grade;

	@ApiModelProperty
	private String category;

	@ApiModelProperty
	private Double scoreLevelOne;

	@ApiModelProperty
	private Double scoreLevelTwo;

	@ApiModelProperty
	private String level;

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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getScoreLevelOne() {
		return scoreLevelOne;
	}

	public void setScoreLevelOne(Double scoreLevelOne) {
		this.scoreLevelOne = scoreLevelOne;
	}

	public Double getScoreLevelTwo() {
		return scoreLevelTwo;
	}

	public void setScoreLevelTwo(Double scoreLevelTwo) {
		this.scoreLevelTwo = scoreLevelTwo;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

}
