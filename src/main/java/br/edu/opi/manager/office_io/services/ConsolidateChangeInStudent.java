package br.edu.opi.manager.office_io.services;

import br.edu.opi.manager.office_io.models.StudentTableRow;
import br.edu.opi.manager.student.services.StudentService;
import br.edu.opi.manager.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsolidateChangeInStudent extends Thread {

	private StudentTableRow student;
	private Long schoolId;

	@Autowired // don't remove, BeanUtil.autowire needs this
	private StudentService studentService;

	public ConsolidateChangeInStudent(Long schoolId, StudentTableRow student) {
		BeanUtil.autowire(this, this.studentService);
		this.student = student;
		this.schoolId = schoolId;
	}

	@Override
	public void run() {
		studentService.solveAndSave(schoolId, student);
	}

}
