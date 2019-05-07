package br.edu.opi.manager.excel_io.exceptions;

public class InvalidGradeRuntimeException extends ExcelIOConflictRuntimeException {

	public InvalidGradeRuntimeException(int cellNum, int rowNum, String value) {
		super("Série '" + value + "' inválida. Série deve ser um dos valores do Menu Dropdown da coluna Série. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_GRADE;
	}
}