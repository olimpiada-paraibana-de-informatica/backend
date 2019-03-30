package br.edu.opi.manager.exceptions.security;

import br.edu.opi.manager.exceptions.ErrorCodes;

public class PasswordNotHashRuntimeException extends AuthenticationRuntimeException {

	public PasswordNotHashRuntimeException() {
		super("Senha não pode ser um hash");
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.PASSWORD_NOT_HASH;
	}

}
