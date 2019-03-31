package br.edu.opi.manager.conventions.dto.user;

import io.swagger.annotations.ApiModelProperty;

public class PrivilegeOutput {

	@ApiModelProperty
	private String key;

	@ApiModelProperty
	private String nameFriendly;

	@ApiModelProperty
	private String category;

	@ApiModelProperty
	private String name;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNameFriendly() {
		return nameFriendly;
	}

	public void setNameFriendly(String nameFriendly) {
		this.nameFriendly = nameFriendly;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
