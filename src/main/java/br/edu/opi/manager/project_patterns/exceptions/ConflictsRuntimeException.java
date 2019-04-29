package br.edu.opi.manager.project_patterns.exceptions;

public abstract class ConflictsRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	public ConflictsRuntimeException() {
	}

	public ConflictsRuntimeException(String message) {
		super(message);
	}

}
