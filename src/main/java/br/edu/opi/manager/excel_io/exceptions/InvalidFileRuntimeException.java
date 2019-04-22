package br.edu.opi.manager.excel_io.exceptions;

public class InvalidFileRuntimeException extends ExcelIOConflictRuntimeException {

	public InvalidFileRuntimeException() {
		super("Arquivo inválido. Favor preencher modelo disponível no site da OPI e submetê-lo em seguida.");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.INVALID_FILE;
	}
}
