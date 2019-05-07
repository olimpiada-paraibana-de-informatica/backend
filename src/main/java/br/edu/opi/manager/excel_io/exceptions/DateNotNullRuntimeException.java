package br.edu.opi.manager.excel_io.exceptions;

public class DateNotNullRuntimeException extends ExcelIOConflictRuntimeException {

	public DateNotNullRuntimeException(int cellNum, int rowNum) {
		super("É preciso informar uma data válida. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.DATE_NOT_NULL;
	}
}