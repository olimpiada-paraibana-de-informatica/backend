package br.edu.opi.manager.student.exception;

import java.time.LocalDate;

public class SchoolNotNullRuntimeException extends StudentConflictRuntimeException {

	private static final long serialVersionUID = -8855688573022904276L;

	public SchoolNotNullRuntimeException(String delegatePrincipal) {
		super("Escola inválida para delegado " + delegatePrincipal);
	}

	public SchoolNotNullRuntimeException(String name, LocalDate dateBirth) {
		super("Favor informar Escola válida para estudante " + name + " nascido em " + dateBirth.getDayOfMonth() + "/" + dateBirth.getMonthValue() + "/" + dateBirth.getYear());
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.SCHOOL_NOT_NULL;
	}

}
