package br.edu.opi.manager.conventions.exceptions;

import br.edu.opi.manager.exceptions.ErrorCodes;
import br.edu.opi.manager.exceptions.ExceptionWithErrorCode;

public class UpdataConflictRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	private static final long serialVersionUID = 748599419111658401L;

	public UpdataConflictRuntimeException(String message) {
		super("Erro ao atualizar recurso. Detalhes: " + message);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.UPDATE_CONFLICT;
	}

}
