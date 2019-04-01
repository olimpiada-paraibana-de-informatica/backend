package br.edu.opi.manager.school.dto;

import br.edu.opi.manager.olympiad.dto.OpiCategoryOutput;
import br.edu.opi.manager.places.dto.CityOutput;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SchoolOutput {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Colégio de Teste")
	private String schoolName;

	@ApiModelProperty(example = "Campina Grande")
	private CityOutput schoolCityOutput;

	@ApiModelProperty(example = "user@user.com")
	private String delegateEmail;

	@ApiModelProperty(example = "Jose")
	private String delegateName;

	@ApiModelProperty(example = "+55 55 55555-5555")
	private String delegatePhone;

	@ApiModelProperty(example = "+55 55 5555-5555")
	private List<OpiCategoryOutput> opiCategories;

	@ApiModelProperty(example = "true")
	private boolean enabled;

	public SchoolOutput() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public CityOutput getSchoolCityOutput() {
		return schoolCityOutput;
	}

	public void setSchoolCityOutput(CityOutput schoolCityOutput) {
		this.schoolCityOutput = schoolCityOutput;
	}

	public String getDelegateEmail() {
		return delegateEmail;
	}

	public void setDelegateEmail(String delegateEmail) {
		this.delegateEmail = delegateEmail;
	}

	public String getDelegateName() {
		return delegateName;
	}

	public void setDelegateName(String delegateName) {
		this.delegateName = delegateName;
	}

	public String getDelegatePhone() {
		return delegatePhone;
	}

	public void setDelegatePhone(String delegatePhone) {
		this.delegatePhone = delegatePhone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<OpiCategoryOutput> getOpiCategories() {
		return opiCategories;
	}

	public void setOpiCategories(List<OpiCategoryOutput> opiCategories) {
		this.opiCategories = opiCategories;
	}

}
