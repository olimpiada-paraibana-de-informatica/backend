package br.edu.opi.manager.person.exceptions;

public class FirstNameCantAbbreviatedRuntimeException extends PersonConflictRuntimeException {

	public FirstNameCantAbbreviatedRuntimeException() {
		super("Primeiro nome não pode ser abreviado");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.FIRST_NAME_ERROR;
	}

}
