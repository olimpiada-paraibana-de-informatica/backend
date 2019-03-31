package br.edu.opi.manager.delegate.model;

import br.edu.opi.manager.conventions.models.Model;
import br.edu.opi.manager.conventions.models.user.Privilege;
import br.edu.opi.manager.conventions.models.user.Profile;
import br.edu.opi.manager.conventions.models.user.User;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@ApiIgnore
@Entity
@Table(name = "delegate")
public class Delegate implements Serializable, User, Model<Long> {

	private static final long serialVersionUID = -9051052759732137812L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@NotEmpty
	@Email
	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@CPF
	@Column(name = "cpf", nullable = false)
	private String cpf;

	@Column(name = "phone")
	private String phone;

	@OneToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

	@Column(name = "locked", nullable = false)
	private boolean locked = false;

	@Column(name = "enabled", nullable = false)
	private boolean enabled = true;

	@Column(name = "expired", nullable = false)
	private boolean expired = false;

	@Column(name = "credentials_expired", nullable = false)
	private boolean credentialsExpired = false;

	@Column(name = "need_change_password", nullable = false)
	private boolean needChangePassword = true;

	@Autowired
	public Delegate() {
	}


	@Override
	public boolean isNeedChangePassword() {
		return needChangePassword;
	}

	@Override
	public void setNeedChangePassword(boolean needChangePassword) {
		this.needChangePassword = needChangePassword;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (profile != null) {
			Set<Privilege> p = profile.getPrivileges();
			int size = p.size();
			// @formatter:off
			String[] privileges = Arrays
					.stream(p.toArray(new Privilege[size]))
					.map(Enum::name)
					.toArray(String[]::new);
			// @formatter:on
			return AuthorityUtils.createAuthorityList(privileges);
		} else {
			return AuthorityUtils.createAuthorityList(new String[0]);
		}
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getCpf() {
		return cpf;
	}

	@Override
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
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

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public Profile getProfile() {
		return profile;
	}

	@Override
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public boolean isLocked() {
		return locked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
}
