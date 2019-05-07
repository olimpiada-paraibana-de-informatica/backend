package br.edu.opi.manager.places.models;

import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_city")
public class City extends Auditing implements Serializable, Model<Long> {

	private static final long serialVersionUID = 537201893569507671L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cbo_code")
	private Long cboCode;

	@Column(name = "name")
	private String name;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uf_cbo_code", nullable = false)
	private State uf;

	public City() {
	}

	public City(Long cboCode) {
		this.cboCode = cboCode;
	}

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

	public State getUf() {
		return uf;
	}

	public void setUf(State uf) {
		this.uf = uf;
	}

}
