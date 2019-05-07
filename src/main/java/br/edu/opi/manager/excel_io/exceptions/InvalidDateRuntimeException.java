package br.edu.opi.manager.excel_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class InvalidDateRuntimeException extends ConflictsRuntimeException {

	public InvalidDateRuntimeException(int cellNum, int rowNum, String value) {
		super("Data '" + value + "' inválida. Verifique na sua planilha se o tipo de dados da coluna é realmente Data. Célula " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_DATE;
	}
}