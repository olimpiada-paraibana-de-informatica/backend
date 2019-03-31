package br.edu.opi.manager.school.model;

import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.olympiad.model.OpiCategory;
import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_school")
public class School extends Auditing implements Serializable, Model<Long> {

	private static final long serialVersionUID = -9051052759732137812L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "cidade", nullable = false)
	private String cidade;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegate_id", nullable = false)
	private Delegate delegate;

	@ElementCollection(targetClass = OpiCategory.class)
	@CollectionTable(name = "tb_schools_opi_category", joinColumns = @JoinColumn(name = "school_id"))
	@Column(name = "name", nullable = false)
	@Enumerated(EnumType.STRING)
	List<OpiCategory> categories;

	@Column(name = "enabled", nullable = false)
	private boolean enabled = false;

	public School() {
	}

	public School(String name, String cidade, Delegate delegate) {
		this.name = name;
		this.cidade = cidade;
		this.delegate = delegate;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Delegate getDelegate() {
		return delegate;
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	public List<OpiCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<OpiCategory> categories) {
		this.categories = categories;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
