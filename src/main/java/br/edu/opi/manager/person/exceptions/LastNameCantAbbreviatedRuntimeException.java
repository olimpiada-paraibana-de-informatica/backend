package br.edu.opi.manager.person.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class LastNameCantAbbreviatedRuntimeException extends ConflictsRuntimeException {

	public LastNameCantAbbreviatedRuntimeException() {
		super("Último nome não pode ser abreviado");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.LAST_NAME_ERROR;
	}

}
