package br.edu.opi.manager.school.model;

import br.edu.opi.manager.conventions.models.Model;
import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.history.model.Auditing;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "school")
public class School extends Auditing implements Serializable, Model<Long>{

	private static final long serialVersionUID = -9051052759732137812L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "delegate_id", nullable = false)
	private Delegate delegate;

//	@OneToMany
//	@JoinColumn(name = "student_id")
//	private List<Student> student;

	@CNPJ
	@Column(name = "cnpj", nullable = false)
	private String cnpj;

	@Column(name = "phone")
	private String phone;

	@Column(name = "enabled", nullable = false)
	private boolean enabled = true;

	public School(){

	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Delegate getDelegate() {
		return delegate;
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

//	public List<Student> getStudent() {
//		return student;
//	}
//
//	public void setStudent(List<Student> student) {
//		this.student = student;
//	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
