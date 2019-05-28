package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.excel_io.models.CompetitorTableRow;
import br.edu.opi.manager.school.models.School;

import java.util.List;

public class ConsolidateChangesInCompetitors extends Thread {

	private List<CompetitorTableRow> rows;
	private School school;

	public ConsolidateChangesInCompetitors(School school, List<CompetitorTableRow> rows) {
		this.rows = rows;
		this.school = school;
	}

	@Override
	public void run() {
		for (CompetitorTableRow student : rows) {
//			new ConsolidateChangeInCompetitor(school, student).start();
			new ConsolidateChangeInCompetitor(school, student).run();
		}
	}

}
