package br.edu.opi.manager.competitor.exceptions;

import br.edu.opi.manager.excel_io.exceptions.ErrorConstants;
import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class WriteFileRuntimeException extends ConflictsRuntimeException {

	public WriteFileRuntimeException() {
		super("Não foi possível gravar dados no momento: erro desconhecido. Favor tentar novamente em instantes.");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.WRITE_FILE_ERROR;
	}

}
