package br.edu.opi.manager.person.exceptions;

public class LastNameCantAbbreviatedRuntimeException extends PersonConflictRuntimeException {

	public LastNameCantAbbreviatedRuntimeException() {
		super("Último nome não pode ser abreviado");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.LAST_NAME_ERROR;
	}

}
