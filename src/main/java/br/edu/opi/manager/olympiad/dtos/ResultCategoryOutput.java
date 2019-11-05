package br.edu.opi.manager.olympiad.dtos;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ResultCategoryOutput {

	@ApiModelProperty
	private List<CompetitorChampionOutput> gold;

	@ApiModelProperty
	private List<CompetitorChampionOutput> silver;

	@ApiModelProperty
	private List<CompetitorChampionOutput> bronze;

	@ApiModelProperty
	private List<CompetitorChampionOutput> honorableMention;

	public ResultCategoryOutput() {
	}

	public ResultCategoryOutput(List<CompetitorChampionOutput> gold, List<CompetitorChampionOutput> silver, List<CompetitorChampionOutput> bronze, List<CompetitorChampionOutput> honorableMention) {
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
		this.honorableMention = honorableMention;
	}

	public List<CompetitorChampionOutput> getGold() {
		return gold;
	}

	public void setGold(List<CompetitorChampionOutput> gold) {
		this.gold = gold;
	}

	public List<CompetitorChampionOutput> getSilver() {
		return silver;
	}

	public void setSilver(List<CompetitorChampionOutput> silver) {
		this.silver = silver;
	}

	public List<CompetitorChampionOutput> getBronze() {
		return bronze;
	}

	public void setBronze(List<CompetitorChampionOutput> bronze) {
		this.bronze = bronze;
	}

	public List<CompetitorChampionOutput> getHonorableMention() {
		return honorableMention;
	}

	public void setHonorableMention(List<CompetitorChampionOutput> honorableMention) {
		this.honorableMention = honorableMention;
	}

}
