package br.edu.opi.manager.conventions.exceptions;

import br.edu.opi.manager.exceptions.ErrorCodes;
import br.edu.opi.manager.exceptions.ExceptionWithErrorCode;

public class CreateConflictRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	private static final long serialVersionUID = -5834281831937441923L;

	public CreateConflictRuntimeException(String message) {
		super("Erro ao criar recurso. Detalhes: " + message);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.CREATE_CONFLICT;
	}

}
