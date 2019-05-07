package br.edu.opi.manager.competitor.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

import java.time.LocalDate;

public class SchoolNotNullRuntimeException extends ConflictsRuntimeException {

	public SchoolNotNullRuntimeException(String delegatePrincipal) {
		super("Delegado " + delegatePrincipal + " não está relacionado com escola alguma");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.SCHOOL_NOT_NULL;
	}

}
