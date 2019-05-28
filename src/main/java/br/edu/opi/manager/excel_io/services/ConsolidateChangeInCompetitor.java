package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.competitor.services.CompetitorService;
import br.edu.opi.manager.excel_io.models.CompetitorTableRow;
import br.edu.opi.manager.school.models.School;
import br.edu.opi.manager.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsolidateChangeInCompetitor extends Thread {

	private CompetitorTableRow competitor;
	private School school;

	@Autowired // don't remove, BeanUtil.autowire needs this
	private CompetitorService competitorService; // TODO: competitor when his CRUD has been implemented

	public ConsolidateChangeInCompetitor(School school, CompetitorTableRow competitor) {
		BeanUtil.autowire(this, this.competitorService);
		this.competitor = competitor;
		this.school = school;
	}

	@Override
	public void run() {
		competitorService.solveAndSave(school, competitor);
	}

}
