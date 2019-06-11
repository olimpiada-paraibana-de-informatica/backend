package br.edu.opi.manager.office_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ConflictsRuntimeException;

public class CreateFileRuntimeException extends ConflictsRuntimeException {

	public CreateFileRuntimeException() {
		super("Não foi possível gerar arquivo no momento: disco temporário cheio. Favor tentar novamente em instantes.");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.CREATE_FILE_ERROR;
	}

}
