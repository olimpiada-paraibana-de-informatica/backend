package br.edu.opi.manager.olympiad.models;

import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_international_champion")
public class InternationalChampion extends Auditing implements Serializable, Model<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "year", nullable = false, updatable = false)
	private int year;

	@Column(name = "nomes")
	private String nomes;

	@Column(name = "school")
	private String school;

	@Column(name = "details")
	private String details;

	public InternationalChampion() {
	}

	public InternationalChampion(int year, String nomes, String school, String details) {
		this.year = year;
		this.nomes = nomes;
		this.school = school;
		this.details = details;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNomes() {
		return nomes;
	}

	public void setNomes(String nomes) {
		this.nomes = nomes;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
