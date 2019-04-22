package br.edu.opi.manager.excel_io.exceptions;

public class NameNotNullRuntimeException extends ExcelIOConflictRuntimeException {
	public NameNotNullRuntimeException(int cellNum, int rowNum) {
		super("É preciso informar um nome válida. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.NAME_NOT_NULL;
	}
}
