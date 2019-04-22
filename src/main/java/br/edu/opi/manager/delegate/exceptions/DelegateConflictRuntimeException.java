package br.edu.opi.manager.delegate.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public abstract class DelegateConflictRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	public DelegateConflictRuntimeException(String message) {
		super(message);
	}

}
