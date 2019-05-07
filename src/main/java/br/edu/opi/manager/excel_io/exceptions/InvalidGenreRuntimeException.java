package br.edu.opi.manager.excel_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class InvalidGenreRuntimeException extends ConflictsRuntimeException {

	public InvalidGenreRuntimeException(int cellNum, int rowNum, String value) {
		super("Gênero '" + value + "' inválido. Gênero deve ser um dos valores do Menu Dropdown da coluna Gênero. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_GENRE;
	}

}