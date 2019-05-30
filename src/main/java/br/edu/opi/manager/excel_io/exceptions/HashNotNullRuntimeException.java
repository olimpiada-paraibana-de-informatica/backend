package br.edu.opi.manager.excel_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class HashNotNullRuntimeException extends ConflictsRuntimeException {

	public HashNotNullRuntimeException(int cellNum, int rowNum) {
		super("É preciso informar um # válido. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.HASH_NOT_NULL;
	}

}
