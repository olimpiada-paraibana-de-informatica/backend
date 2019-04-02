package br.edu.opi.manager.school.exception;

public class SchoolExistsRuntimeException extends SchoolConflictsRuntimeException {

	public SchoolExistsRuntimeException(String schoolName) {
		super("Este delegado já está reponsável por " + schoolName);
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.DELEGATE_ALREADY_EXISTS;
	}

}
