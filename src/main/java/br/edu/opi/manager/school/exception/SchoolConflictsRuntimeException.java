package br.edu.opi.manager.school.exception;

import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public abstract class SchoolConflictsRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	public SchoolConflictsRuntimeException(String message) {
		super(message);
	}

}
