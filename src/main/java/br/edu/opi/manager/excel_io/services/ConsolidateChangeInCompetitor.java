package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.competitor.services.CompetitorService;
import br.edu.opi.manager.excel_io.models.CompetitorTableRow;
import br.edu.opi.manager.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsolidateChangeInCompetitor extends Thread {

	private CompetitorTableRow competitor;
	private Long schoolId;

	@Autowired // don't remove, BeanUtil.autowire needs this
	private CompetitorService competitorService; // TODO: competitor when his CRUD has been implemented

	public ConsolidateChangeInCompetitor(Long schoolId, CompetitorTableRow competitor) {
		BeanUtil.autowire(this, this.competitorService);
		this.competitor = competitor;
		this.schoolId = schoolId;
	}

	@Override
	public void run() {
		competitorService.solveAndSave(schoolId, competitor);
	}

}
