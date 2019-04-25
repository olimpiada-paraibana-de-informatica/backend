package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.excel_io.models.CompetitorTableRow;

import java.util.List;

public class ConsolidateChangesInCompetitors extends Thread {

	private List<CompetitorTableRow> rows;

	public ConsolidateChangesInCompetitors(List<CompetitorTableRow> rows) {
		this.rows = rows;
	}

	@Override
	public void run() {
		for (CompetitorTableRow student : rows) {
			new ConsolidateChangeInCompetitor(student).start();
		}
	}

}
