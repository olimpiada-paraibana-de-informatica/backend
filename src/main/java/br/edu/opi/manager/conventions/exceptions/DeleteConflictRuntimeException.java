package br.edu.opi.manager.conventions.exceptions;

import br.edu.opi.manager.exceptions.ErrorCodes;
import br.edu.opi.manager.exceptions.ExceptionWithErrorCode;

public class DeleteConflictRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	private static final long serialVersionUID = -8769718520588585221L;

	public DeleteConflictRuntimeException(String message) {
		super("Erro ao remover recurso. Detalhes: " + message);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.DELETE_CONFLICT;
	}

}
