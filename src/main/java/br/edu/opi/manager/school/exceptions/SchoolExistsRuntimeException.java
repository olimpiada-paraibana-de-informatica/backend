package br.edu.opi.manager.school.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class SchoolExistsRuntimeException extends ConflictsRuntimeException {

	public SchoolExistsRuntimeException(String schoolName) {
		super("Este delegado já está reponsável por " + schoolName);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.DELEGATE_ALREADY_EXISTS;
	}

}
