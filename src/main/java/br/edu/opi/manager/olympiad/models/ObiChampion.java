package br.edu.opi.manager.olympiad.models;

import br.edu.opi.manager.person.models.Person;
import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_obi_champion")
@EntityListeners(ObiChampionListener.class)
public class ObiChampion extends Auditing implements Serializable, Model<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	@JoinColumn(name = "person_id", nullable = false, updatable = false)
	private Person person;

	@Column(name = "year", nullable = false, updatable = false)
	private int year;

	@Enumerated(EnumType.STRING)
	@Column(name = "obi_award", nullable = false, updatable = false)
	private ObiAward obiAward;

	@Enumerated(EnumType.STRING)
	@Column(name = "obi_category", nullable = false, updatable = false)
	private ObiCategory obiCategory;

	@Column(name = "position")
	private Integer position;

	public ObiChampion() {
	}

	public ObiChampion(Person person, int year, ObiAward obiAward, ObiCategory obiCategory, Integer position) {
		this.person = person;
		this.year = year;
		this.obiAward = obiAward;
		this.obiCategory = obiCategory;
		this.position = position;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ObiAward getObiAward() {
		return obiAward;
	}

	public void setObiAward(ObiAward obiAward) {
		this.obiAward = obiAward;
	}

	public ObiCategory getObiCategory() {
		return obiCategory;
	}

	public void setObiCategory(ObiCategory obiCategory) {
		this.obiCategory = obiCategory;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

}
