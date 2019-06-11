package br.edu.opi.manager.office_io.services;

import br.edu.opi.manager.office_io.models.CompetitorTableRow;

import java.util.List;

public class UpdateCompetitorsLevelTwo extends Thread {

	private List<CompetitorTableRow> rows;

	public UpdateCompetitorsLevelTwo(List<CompetitorTableRow> rows) {
		this.rows = rows;
	}

	@Override
	public void run() {
		for (CompetitorTableRow competidor : rows) {
//			new UpdateCompetitorLevelTwo(competidor).start();
			new UpdateCompetitorLevelTwo(competidor).run();
		}
	}

}
