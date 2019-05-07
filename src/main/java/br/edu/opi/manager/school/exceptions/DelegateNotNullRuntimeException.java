package br.edu.opi.manager.school.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;
import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public class DelegateNotNullRuntimeException extends ConflictsRuntimeException {

	public DelegateNotNullRuntimeException() {
		super("Favor informar um Delegado");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.DELEGATE_NOT_NULL;
	}

}
