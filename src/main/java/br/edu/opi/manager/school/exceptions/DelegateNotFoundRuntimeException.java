package br.edu.opi.manager.school.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class DelegateNotFoundRuntimeException extends ConflictsRuntimeException {

	public DelegateNotFoundRuntimeException(String id) {
		super("Delegado de identificador " + id + " não existe na base de dados");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.DELEGATE_NOT_FOUND;
	}

}
