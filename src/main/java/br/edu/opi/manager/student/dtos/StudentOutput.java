package br.edu.opi.manager.student.dtos;

import br.edu.opi.manager.person.models.Genre;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class StudentOutput {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "João da Silva Santos")
	private String name;

	@ApiModelProperty(example = "1998-03-25")
	private LocalDate dateBirth;

	@ApiModelProperty
	@NotEmpty
	private Genre genre;

	@ApiModelProperty(example = "true")
	private boolean enabled;

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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
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
