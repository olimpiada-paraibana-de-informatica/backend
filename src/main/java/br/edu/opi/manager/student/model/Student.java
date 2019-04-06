package br.edu.opi.manager.student.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.olympiad.model.OpiCategory;
import br.edu.opi.manager.places.model.City;
import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

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
	
	@Column(name = "dateBirth", nullable = false)
	private LocalDate dateBirth;
	
	@Column(name = "genre", nullable = false)
	private String genre;
	
	@Column(name="degree", nullable = false)
	private String degree;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled = false;
	
	
	public Student(String name, LocalDate dateBirth, String genre, String degree) {
		this.name = name;
		this.dateBirth = dateBirth;
		this.genre = genre;
		this.degree = degree;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

}
