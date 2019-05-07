package br.edu.opi.manager.excel_io.exceptions;

public class InvalidDateRuntimeException extends ExcelIOConflictRuntimeException {

	public InvalidDateRuntimeException(int cellNum, int rowNum, String value) {
		super("Data '" + value + "' inválida. Verifique na sua planilha se o tipo de dados da coluna é realmente Data. Célula " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_DATE;
	}
}