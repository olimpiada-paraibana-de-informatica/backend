package br.edu.opi.manager.excel_io.services;

import br.edu.opi.manager.excel_io.models.StudentTableRow;
import br.edu.opi.manager.student.services.StudentService;
import br.edu.opi.manager.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsolidateChangeInStudent extends Thread {

	private StudentTableRow student;

	@Autowired // don't remove, BeanUtil.autowire needs this
	private StudentService studentService; // TODO: competitor when his CRUD has been implemented

	public ConsolidateChangeInStudent(StudentTableRow student) {
		BeanUtil.autowire(this, this.studentService);
		this.student = student;
	}

	@Override
	public void run() {
		studentService.solveAndSave(student);
	}

}
