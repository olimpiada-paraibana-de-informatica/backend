package br.edu.opi.manager.student.model;

import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;
import br.edu.opi.manager.school.model.School;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student extends Auditing implements Serializable, Model<Long> {

	private static final long serialVersionUID = -9051052759732137812L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "date_birth", nullable = false)
	private LocalDate dateBirth;

	@Column(name = "genre", nullable = false)
	private Genre genre;

	@Column(name = "enabled", nullable = false)
	private boolean enabled = true;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id", nullable = false, updatable = false)
	// TODO: check what happens when the student changes his school
	private School school;

	public Student() {
	}

	public Student(String name, LocalDate dateBirth, Genre genre, School school) {
		this.name = name;
		this.dateBirth = dateBirth;
		this.genre = genre;
		this.school = school;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
