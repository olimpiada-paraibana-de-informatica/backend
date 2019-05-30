package br.edu.opi.manager.school.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class SchoolNotFoundRuntimeException extends ConflictsRuntimeException {

	public SchoolNotFoundRuntimeException() {
		super("Escola não encontrada");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.SCHOOL_NOT_NULL;
	}

}
