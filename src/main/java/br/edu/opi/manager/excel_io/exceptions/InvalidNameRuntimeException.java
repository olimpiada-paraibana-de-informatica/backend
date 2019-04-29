package br.edu.opi.manager.excel_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class InvalidNameRuntimeException extends ConflictsRuntimeException {

	public InvalidNameRuntimeException(int cellNum, int rowNum, String value) {
		super("Nome '" + value + "' inválido. Célula " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_NAME;
	}

}
