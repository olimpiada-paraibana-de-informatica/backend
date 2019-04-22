package br.edu.opi.manager.excel_io.exceptions;

public class GenreNotNullRuntimeException extends ExcelIOConflictRuntimeException {

	public GenreNotNullRuntimeException(int cellNum, int rowNum) {
		super("É preciso informar um gênero válido. Célula: " + ExceptionsUtils.solveLetterColumn(cellNum) + rowNum);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.GENRE_NOT_NULL;
	}
}