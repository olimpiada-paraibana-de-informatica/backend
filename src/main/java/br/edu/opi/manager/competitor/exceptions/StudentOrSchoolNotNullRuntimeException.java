package br.edu.opi.manager.competitor.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class StudentOrSchoolNotNullRuntimeException extends ConflictsRuntimeException {

	public StudentOrSchoolNotNullRuntimeException() {
		super("Estudante ou Escola não determinados");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.STUDENT_SCHOOL_NOT_FOUND;
	}

}
