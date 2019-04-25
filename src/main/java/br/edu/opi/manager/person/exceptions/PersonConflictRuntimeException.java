package br.edu.opi.manager.person.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public abstract class PersonConflictRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	public PersonConflictRuntimeException() {
		super();
	}

	public PersonConflictRuntimeException(String message) {
		super(message);
	}

}
