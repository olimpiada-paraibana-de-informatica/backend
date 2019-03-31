package br.edu.opi.manager.project_patterns.exceptions;

public class NotFoundRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	private static final long serialVersionUID = -2187500009249884414L;

	public NotFoundRuntimeException() {
		super("Recurso não encontrado");
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.NOT_FOUND;
	}

}
