package br.edu.opi.manager.person.exceptions;

public class ItsNotFullNameRuntimeException extends PersonConflictRuntimeException {

	public ItsNotFullNameRuntimeException() {
		super("Favor informar nome completo");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.NOT_FULL_NAME;
	}

}
