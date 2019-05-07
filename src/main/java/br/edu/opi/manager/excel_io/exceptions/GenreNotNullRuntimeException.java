package br.edu.opi.manager.excel_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class GenreNotNullRuntimeException extends ConflictsRuntimeException {

	public GenreNotNullRuntimeException(int cellNum, int rowNum) {
		super("É preciso informar um gênero válido. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.GENRE_NOT_NULL;
	}
}