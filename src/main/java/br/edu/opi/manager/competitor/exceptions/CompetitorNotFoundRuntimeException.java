package br.edu.opi.manager.competitor.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class CompetitorNotFoundRuntimeException extends ConflictsRuntimeException {

	public CompetitorNotFoundRuntimeException() {
		super("Competidor não encotrado");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.COMPETITOR_NOT_FOUND;
	}

}
