package br.edu.opi.manager.person.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class FirstNameCantAbbreviatedRuntimeException extends ConflictsRuntimeException {

	public FirstNameCantAbbreviatedRuntimeException() {
		super("Primeiro nome não pode ser abreviado");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.FIRST_NAME_ERROR;
	}

}
