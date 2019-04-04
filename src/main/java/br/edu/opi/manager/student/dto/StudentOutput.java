package br.edu.opi.manager.student.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;

public class StudentOutput {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "João da Silva Santos")
	private String name;
	
	@ApiModelProperty(example = "28/10/1822")
	private LocalDate dateBirth;
	
	@ApiModelProperty(example = "M")
	private String genre;
	
	@ApiModelProperty(example = "8 série")
	private String degree;
	
	@ApiModelProperty(example = "true")
	private boolean enabled = false;
	

	public StudentOutput() {
		
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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
