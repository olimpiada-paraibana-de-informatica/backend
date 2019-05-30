package br.edu.opi.manager.delegate.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class DelegateNotFoundRuntimeException extends ConflictsRuntimeException {

	public DelegateNotFoundRuntimeException(String id) {
		super("Delegado " + id + " não existe na base de dados");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.DELEGATE_NOT_FOUND;
	}

}
