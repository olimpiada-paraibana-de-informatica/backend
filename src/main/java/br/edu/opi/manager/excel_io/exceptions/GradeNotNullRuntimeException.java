package br.edu.opi.manager.excel_io.exceptions;

public class GradeNotNullRuntimeException extends ExcelIOConflictRuntimeException {

	public GradeNotNullRuntimeException(int cellNum, int rowNum) {
		super("É preciso informar uma série válida. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.GRADE_NOT_NULL;
	}

}