package br.edu.opi.manager.excel_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class DateNotNullRuntimeException extends ConflictsRuntimeException {

	public DateNotNullRuntimeException(int cellNum, int rowNum) {
		super("É preciso informar uma data válida. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.DATE_NOT_NULL;
	}

}