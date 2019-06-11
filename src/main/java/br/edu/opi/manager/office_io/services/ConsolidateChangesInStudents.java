package br.edu.opi.manager.office_io.services;

import br.edu.opi.manager.office_io.models.StudentTableRow;

import java.util.List;

public class ConsolidateChangesInStudents extends Thread {

	private List<StudentTableRow> rows;
	private Long schoolId;

	public ConsolidateChangesInStudents(Long schoolId, List<StudentTableRow> rows) {
		this.rows = rows;
		this.schoolId = schoolId;
	}

	@Override
	public void run() {
		for (StudentTableRow student : rows) {
//			new ConsolidateChangeInCompetitor(schoolId, student).start();
			new ConsolidateChangeInStudent(schoolId, student).run();
		}
	}

}
