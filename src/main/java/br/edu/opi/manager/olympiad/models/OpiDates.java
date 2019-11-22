package br.edu.opi.manager.olympiad.models;

import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_opi_dates")
public class OpiDates extends Auditing implements Serializable, Model<Long> {

	public static long OPI_DATES_ID = 1;

	@Id
	@Column(name = "id")
	private Long id = OPI_DATES_ID;

	@Column(name = "subscribe_date")
	private String subscribeDate;

	@Column(name = "iniciacao_1_and_2_pub_level_one_date")
	private String iniciacao1And2PubLevelOneDate;

	@Column(name = "iniciacao_1_and_2_level_one_date")
	private String iniciacao1And2LevelOneDate;

	@Column(name = "programacao_level_one_date")
	private String programacaoLevelOneDate;

	@Column(name = "iniciacao_1_and_2_level_two_date")
	private String iniciacao1And2LevelTwoDate;

	@Column(name = "programacao_level_two_date")
	private String programacaoLevelTwoDate;

	@Column(name = "results_date")
	private String resultsDate;

	@Column(name = "award_date")
	private String awardDate;

	@Column(name = "high_subscribe_date")
	private String highSubscribeDate;

	@Column(name = "high_olimpiad_date")
	private String highOlimpiadDate;

	@Column(name = "high_results_date")
	private String highResultsDate;

	@Column(name = "high_award_date")
	private String highAwardDate;

	public OpiDates() {
	}

	public OpiDates(String subscribeDate, String iniciacao1And2PubLevelOneDate, String iniciacao1And2LevelOneDate, String programacaoLevelOneDate, String iniciacao1And2LevelTwoDate, String programacaoLevelTwoDate, String resultsDate, String awardDate, String highSubscribeDate, String highOlimpiadDate, String highResultsDate, String highAwardDate) {
		this.subscribeDate = subscribeDate;
		this.iniciacao1And2PubLevelOneDate = iniciacao1And2PubLevelOneDate;
		this.iniciacao1And2LevelOneDate = iniciacao1And2LevelOneDate;
		this.programacaoLevelOneDate = programacaoLevelOneDate;
		this.iniciacao1And2LevelTwoDate = iniciacao1And2LevelTwoDate;
		this.programacaoLevelTwoDate = programacaoLevelTwoDate;
		this.resultsDate = resultsDate;
		this.awardDate = awardDate;
		this.highSubscribeDate = highSubscribeDate;
		this.highOlimpiadDate = highOlimpiadDate;
		this.highResultsDate = highResultsDate;
		this.highAwardDate = highAwardDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = OPI_DATES_ID;
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
