package br.edu.opi.manager.excel_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class InvalidFileRuntimeException extends ConflictsRuntimeException {

	public InvalidFileRuntimeException() {
		super("Arquivo inválido. Favor preencher modelo disponível no site da OPI e submetê-lo em seguida.");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_FILE;
	}
}
