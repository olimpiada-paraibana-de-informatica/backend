package br.edu.opi.manager.security.exceptions;

public class PasswordNotHashRuntimeException extends AuthenticationRuntimeException {

	public PasswordNotHashRuntimeException() {
		super("Senha não pode ser um hash");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.PASSWORD_NOT_HASH;
	}

}
