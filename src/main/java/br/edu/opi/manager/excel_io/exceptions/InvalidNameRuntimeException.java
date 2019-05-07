package br.edu.opi.manager.excel_io.exceptions;

public class InvalidNameRuntimeException extends ExcelIOConflictRuntimeException {

	public InvalidNameRuntimeException(int cellNum, int rowNum, String value) {
		super("Nome '" + value + "' inválido. Célula " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_NAME;
	}

}
