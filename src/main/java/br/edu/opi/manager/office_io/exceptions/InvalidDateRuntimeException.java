package br.edu.opi.manager.office_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class InvalidDateRuntimeException extends ConflictsRuntimeException {

	public InvalidDateRuntimeException(int cellNum, int rowNum, String value) {
		super("Data '" + value + "' inválida. Verifique na sua planilha se o tipo de dados da coluna é realmente Data. Célula " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_DATE;
	}
}