package br.edu.opi.manager.excel_io.exceptions;

public class InvalidGenreRuntimeException extends ExcelIOConflictRuntimeException {

	public InvalidGenreRuntimeException(int cellNum, int rowNum, String value) {
		super("Gênero '" + value + "' inválido. Gênero deve ser um dos valores do Menu Dropdown da coluna Gênero. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_GENRE;
	}

}