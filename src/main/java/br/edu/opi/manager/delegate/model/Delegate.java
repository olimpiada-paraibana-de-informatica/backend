package br.edu.opi.manager.delegate.model;

import br.edu.opi.manager.project_patterns.models.user.ProfileFactory;
import br.edu.opi.manager.user.model.UserModel;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@ApiIgnore
@Entity
@Table(name = "tb_delegate")
@PrimaryKeyJoinColumn(name = "id")
public class Delegate extends UserModel {

	private static final long serialVersionUID = -9051052759732137812L;

	@Column(name = "phone")
	private String phone;

	public Delegate() {
		super();
	}

	public Delegate(Long delegateId) {
		super(delegateId);
	}

	public Delegate(Long delegateId, String delegateUsername) {
		super(delegateId);
		super.setUsername(delegateUsername);
	}

	public Delegate(Long delegateId, String delegateEmail, String delegateName) {
		super(delegateId);
		super.setUsername(delegateEmail);
		super.setName(delegateName);
		super.setProfile(ProfileFactory.delegateUser());
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
