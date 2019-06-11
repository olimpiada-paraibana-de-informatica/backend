package br.edu.opi.manager.office_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class InvalidGradeRuntimeException extends ConflictsRuntimeException {

	public InvalidGradeRuntimeException(int cellNum, int rowNum, String value) {
		super("Série '" + value + "' inválida. Série deve ser um dos valores do Menu Dropdown da coluna Série. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_GRADE;
	}
}