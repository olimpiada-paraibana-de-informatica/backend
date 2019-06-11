package br.edu.opi.manager.office_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class DateNotNullRuntimeException extends ConflictsRuntimeException {

	public DateNotNullRuntimeException(int cellNum, int rowNum) {
		super("É preciso informar uma data válida. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.DATE_NOT_NULL;
	}

}