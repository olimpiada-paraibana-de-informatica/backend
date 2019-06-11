package br.edu.opi.manager.office_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class InvalidNameRuntimeException extends ConflictsRuntimeException {

	public InvalidNameRuntimeException(int cellNum, int rowNum, String value) {
		super("Nome '" + value + "' inválido. Célula " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_NAME;
	}

}
