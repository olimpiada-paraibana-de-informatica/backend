package br.edu.opi.manager.places.model;

import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_state")
public class State extends Auditing implements Serializable, Model<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cbo_code")
	private Long cboCode;

	@Column(name = "name")
	private String name;

	@Column(name = "abbreviation")
	private String abbreviation;

	@Override
	public Long getId() {
		return cboCode;
	}

	@Override
	public void setId(Long id) {
		this.cboCode = id;
	}

	public Long getCboCode() {
		return cboCode;
	}

	public void setCboCode(Long cboCode) {
		this.cboCode = cboCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

}
