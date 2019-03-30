package br.edu.opi.manager.user.model;

import br.edu.opi.manager.conventions.models.Model;
import br.edu.opi.manager.conventions.models.user.Privilege;
import br.edu.opi.manager.conventions.models.user.Profile;
import br.edu.opi.manager.conventions.models.user.User;
import br.edu.opi.manager.history.model.Auditing;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * Simple UserModel.
 */
// @formatter:off
@ApiIgnore
@Entity
@Table(name = "tb_user")
@EntityListeners({UserListener.class})
//@formatter:on
public class UserModel extends Auditing implements Serializable, User, Model<Long> {

	private static final long serialVersionUID = 2967515815580857179L;

	static final String FK_PROFILE_USER = "FK_PROFILE_USER";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty
	@Email
	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToOne
	@JoinColumn(name = "profile_id", foreignKey = @ForeignKey(name = FK_PROFILE_USER))
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

	protected UserModel() {
	}

	public UserModel(String username, String password, String firstName, String lastName, String cpf, Profile profile) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.profile = profile;
	}

	protected UserModel(Long id, Profile profile) {
		this();
		this.id = id;
		this.profile = profile;
	}

	protected UserModel(Profile profile) {
		this();
		this.profile = profile;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public boolean isNeedChangePassword() {
		return needChangePassword;
	}

	public void setNeedChangePassword(boolean needChangePassword) {
		this.needChangePassword = needChangePassword;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isAccountNonExpired() {
		return !expired;
	}

	public boolean isAccountNonLocked() {
		return !locked;
	}

	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserModel user = (UserModel) o;
		return Objects.equals(username, user.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public String toString() {
		return username;
	}

}
