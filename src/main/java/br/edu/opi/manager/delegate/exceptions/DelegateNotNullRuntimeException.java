package br.edu.opi.manager.delegate.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class DelegateNotNullRuntimeException extends ConflictsRuntimeException {

	private static final long serialVersionUID = -8855688573022904276L;

	public DelegateNotNullRuntimeException() {
		super("Delegado deve ter um e-mail válido");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.DELEGATE_NOT_NULL;
	}

}
