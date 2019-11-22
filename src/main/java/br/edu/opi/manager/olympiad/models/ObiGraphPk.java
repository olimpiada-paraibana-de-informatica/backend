package br.edu.opi.manager.olympiad.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class ObiGraphPk implements Serializable {

	@Column(name = "year", nullable = false, updatable = false)
	private Integer year;

	@Enumerated(EnumType.STRING)
	@Column(name = "obi_category", nullable = false, updatable = false)
	private ObiCategory obiCategory;

	public ObiGraphPk() {
	}

	public ObiGraphPk(Integer year, ObiCategory obiCategory) {
		this.year = year;
		this.obiCategory = obiCategory;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public ObiCategory getObiCategory() {
		return obiCategory;
	}

	public void setObiCategory(ObiCategory obiCategory) {
		this.obiCategory = obiCategory;
	}

}
