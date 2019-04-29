package br.edu.opi.manager.competitor.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class CompetitorAlreadyExistsRuntimeException extends ConflictsRuntimeException {

	public CompetitorAlreadyExistsRuntimeException() {
		super("Competidor já está cadastrado");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.COMPETITOR_ALREADY_EXISTS;
	}

}
