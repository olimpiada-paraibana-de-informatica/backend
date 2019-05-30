package br.edu.opi.manager.excel_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class InvalidHashRuntimeException extends ConflictsRuntimeException {

	public InvalidHashRuntimeException(int cellNum, int rowNum, String value) {
		super("# '" + value + "' inválido. Célula " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_HASH;
	}

}
