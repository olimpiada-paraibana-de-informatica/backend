package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.excel_io.models.StudentTableRow;

import java.util.List;

public class ConsolidateChangesInStudents extends Thread {

	private List<StudentTableRow> rows;

	public ConsolidateChangesInStudents(List<StudentTableRow> rows) {
		this.rows = rows;
	}

	@Override
	public void run() {
		for (StudentTableRow student : rows) {
			new ConsolidateChangeInStudent(student).start();
		}
	}

}
