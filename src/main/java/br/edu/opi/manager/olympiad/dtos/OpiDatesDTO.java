package br.edu.opi.manager.olympiad.dtos;

import io.swagger.annotations.ApiModelProperty;

public class OpiDatesDTO {

	@ApiModelProperty
	private String subscribeDate;

	@ApiModelProperty
	private String iniciacao1And2PubLevelOneDate;

	@ApiModelProperty
	private String iniciacao1And2LevelOneDate;

	@ApiModelProperty
	private String programacaoLevelOneDate;

	@ApiModelProperty
	private String iniciacao1And2LevelTwoDate;

	@ApiModelProperty
	private String programacaoLevelTwoDate;

	@ApiModelProperty
	private String resultsDate;

	@ApiModelProperty
	private String awardDate;

	@ApiModelProperty
	private String highSubscribeDate;

	@ApiModelProperty
	private String highOlimpiadDate;

	@ApiModelProperty
	private String highResultsDate;

	@ApiModelProperty
	private String highAwardDate;

	public OpiDatesDTO() {
	}

	public String getSubscribeDate() {
		return subscribeDate;
	}

	public void setSubscribeDate(String subscribeDate) {
		this.subscribeDate = subscribeDate;
	}

	public String getIniciacao1And2PubLevelOneDate() {
		return iniciacao1And2PubLevelOneDate;
	}

	public void setIniciacao1And2PubLevelOneDate(String iniciacao1And2PubLevelOneDate) {
		this.iniciacao1And2PubLevelOneDate = iniciacao1And2PubLevelOneDate;
	}

	public String getIniciacao1And2LevelOneDate() {
		return iniciacao1And2LevelOneDate;
	}

	public void setIniciacao1And2LevelOneDate(String iniciacao1And2LevelOneDate) {
		this.iniciacao1And2LevelOneDate = iniciacao1And2LevelOneDate;
	}

	public String getProgramacaoLevelOneDate() {
		return programacaoLevelOneDate;
	}

	public void setProgramacaoLevelOneDate(String programacaoLevelOneDate) {
		this.programacaoLevelOneDate = programacaoLevelOneDate;
	}

	public String getIniciacao1And2LevelTwoDate() {
		return iniciacao1And2LevelTwoDate;
	}

	public void setIniciacao1And2LevelTwoDate(String iniciacao1And2LevelTwoDate) {
		this.iniciacao1And2LevelTwoDate = iniciacao1And2LevelTwoDate;
	}

	public String getProgramacaoLevelTwoDate() {
		return programacaoLevelTwoDate;
	}

	public void setProgramacaoLevelTwoDate(String programacaoLevelTwoDate) {
		this.programacaoLevelTwoDate = programacaoLevelTwoDate;
	}

	public String getResultsDate() {
		return resultsDate;
	}

	public void setResultsDate(String resultsDate) {
		this.resultsDate = resultsDate;
	}

	public String getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
	}

	public String getHighSubscribeDate() {
		return highSubscribeDate;
	}

	public void setHighSubscribeDate(String highSubscribeDate) {
		this.highSubscribeDate = highSubscribeDate;
	}

	public String getHighOlimpiadDate() {
		return highOlimpiadDate;
	}

	public void setHighOlimpiadDate(String highOlimpiadDate) {
		this.highOlimpiadDate = highOlimpiadDate;
	}

	public String getHighResultsDate() {
		return highResultsDate;
	}

	public void setHighResultsDate(String highResultsDate) {
		this.highResultsDate = highResultsDate;
	}

	public String getHighAwardDate() {
		return highAwardDate;
	}

	public void setHighAwardDate(String highAwardDate) {
		this.highAwardDate = highAwardDate;
	}

}
