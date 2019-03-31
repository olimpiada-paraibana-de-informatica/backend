package br.edu.opi.manager.conventions.dto.user;

import br.edu.opi.manager.history.dto.AuditingOutput;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

public class ProfileUserOutput extends AuditingOutput {

	@ApiModelProperty(required = true)
	private Long id;

	@ApiModelProperty(example = "Teste", required = true)
	private String name;

	Set<PrivilegeOutput> privileges;

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

	public Set<PrivilegeOutput> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<PrivilegeOutput> privileges) {
		this.privileges = privileges;
	}

}