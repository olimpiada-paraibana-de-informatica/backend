package br.edu.opi.manager.conventions.models.user;

import br.edu.opi.manager.conventions.models.Model;
import br.edu.opi.manager.history.model.Auditing;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@ApiIgnore
@Entity
@Table(name = "tb_profile")
public class Profile extends Auditing implements Serializable, Model<Long> {

	private static final String FK_PROFILE_PRIVILEGE = "FK_PROFILE_PRIVILEGE";

	private static final long serialVersionUID = -7329249192042135115L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty
	@NotNull
	@Column(name = "name")
	private String name;

	@ElementCollection(targetClass = Privilege.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_privilege", joinColumns = @JoinColumn(name = "profile_id"), foreignKey = @ForeignKey(name = FK_PROFILE_PRIVILEGE))
	@Column(name = "name", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<Privilege> privileges;

	public Profile() {
	}

	public Profile(Long id) {
		this.id = id;
	}

	public Profile(String name, Set<Privilege> privileges) {
		this.name = name;
		this.privileges = privileges;
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

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(final Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}
