package br.edu.opi.manager.student.dto;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;

import br.edu.opi.manager.student.model.Student;
import io.swagger.annotations.ApiModelProperty;

public class StudentInput {
	
	private String name;
	
	private LocalDate dateBirth;
	
	private String genre;
	
	private String degree;
	
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


	
}
