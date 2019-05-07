package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.excel_io.models.CompetitorTableRow;
import br.edu.opi.manager.school.models.School;

import java.util.List;

public class ConsolidateChangesInCompetitors extends Thread {

	private List<CompetitorTableRow> rows;
	private Long schoolId;

	public ConsolidateChangesInCompetitors(Long schoolId, List<CompetitorTableRow> rows) {
		this.rows = rows;
		this.schoolId = schoolId;
	}

	@Override
	public void run() {
		for (CompetitorTableRow student : rows) {
//			new ConsolidateChangeInCompetitor(schoolId, student).start();
			new ConsolidateChangeInCompetitor(schoolId, student).run();
		}
	}

}
