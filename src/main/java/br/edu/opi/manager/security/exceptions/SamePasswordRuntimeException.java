package br.edu.opi.manager.security.exceptions;

public class SamePasswordRuntimeException extends AuthenticationRuntimeException {

	private static final long serialVersionUID = -1661246560469605932L;

	public SamePasswordRuntimeException() {
		super("Nova senha deve ser diferente da anterior");
	}

	@Override
	public String getErrorCode() {
		return ErrorConstants.SAME_PASSWORD;
	}

}
