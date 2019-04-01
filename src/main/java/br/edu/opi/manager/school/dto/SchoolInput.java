package br.edu.opi.manager.school.dto;

import br.edu.opi.manager.olympiad.model.OpiCategory;
import br.edu.opi.manager.places.model.City;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import java.util.List;

@ApiModel(value = "schoolInput")
public class SchoolInput {

	@ApiModelProperty(example = "Colégio de Teste", required = true)
	private String schoolName;

	@ApiModelProperty(example = "2504009", required = true)
	private Long schoolCityCbo;

	@Email
	@ApiModelProperty(example = "delegate@email.com", required = true)
	private String delegateEmail;

	@ApiModelProperty(example = "Delegado Exemplo")
	private String delegateName;

//	@ApiModelProperty(example = "1")
//	private Long delegateId;

	@ApiModelProperty("[\"INICIACAO_1\"]")
	private List<OpiCategory> opiCategories;

	public SchoolInput() {
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Long getSchoolCityCbo() {
		return schoolCityCbo;
	}

	public void setSchoolCityCbo(Long schoolCityCbo) {
		this.schoolCityCbo = schoolCityCbo;
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

//	public Long getDelegateId() {
//		return delegateId;
//	}
//
//	public void setDelegateId(Long delegateId) {
//		this.delegateId = delegateId;
//	}

	public List<OpiCategory> getOpiCategories() {
		return opiCategories;
	}

	public void setOpiCategories(List<OpiCategory> opiCategories) {
		this.opiCategories = opiCategories;
	}

}
