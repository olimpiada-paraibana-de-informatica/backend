package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.competitor.services.CompetitorService;
import br.edu.opi.manager.excel_io.models.CompetitorTableRow;
import br.edu.opi.manager.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateCompetitorLevelTwo extends Thread {

	private CompetitorTableRow competitor;

	@Autowired // don't remove, BeanUtil.autowire needs this
	private CompetitorService competitorService;

	public UpdateCompetitorLevelTwo(CompetitorTableRow competitor) {
		BeanUtil.autowire(this, this.competitorService);
		this.competitor = competitor;
	}

	@Override
	public void run() {
		competitorService.solveAndUpdate(competitor);
	}

}
