package br.edu.opi.manager.user.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "userProfileInput")
public class UserProfileInput {

	@ApiModelProperty(example = "1", required = true)
	public Long userId;

	@ApiModelProperty(example = "1", required = true)
	private Long profileId;

	public UserProfileInput() {
	}

	public UserProfileInput(Long userId, Long profileId) {
		this.userId = userId;
		this.profileId = profileId;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
