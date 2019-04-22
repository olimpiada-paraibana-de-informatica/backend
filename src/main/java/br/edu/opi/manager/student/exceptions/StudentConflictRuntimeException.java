package br.edu.opi.manager.student.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public abstract class StudentConflictRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	public StudentConflictRuntimeException(String message) {
		super(message);
	}

}
