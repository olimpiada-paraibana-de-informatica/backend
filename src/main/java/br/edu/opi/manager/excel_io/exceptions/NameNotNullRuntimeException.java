package br.edu.opi.manager.excel_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class NameNotNullRuntimeException extends ConflictsRuntimeException {

	public NameNotNullRuntimeException(int cellNum, int rowNum) {
		super("É preciso informar um nome válida. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.NAME_NOT_NULL;
	}

}
