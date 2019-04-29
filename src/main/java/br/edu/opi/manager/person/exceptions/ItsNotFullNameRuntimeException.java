package br.edu.opi.manager.person.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class ItsNotFullNameRuntimeException extends ConflictsRuntimeException {

	public ItsNotFullNameRuntimeException() {
		super("Favor informar nome completo");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.NOT_FULL_NAME;
	}

}
