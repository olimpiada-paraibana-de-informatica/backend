package br.edu.opi.manager.exceptions;

public class ErrorCodes {

	/* Conventions */
	public static final String NOT_FOUND = "NOT_FOUND";
	public static final String CREATE_CONFLICT = "CREATE_CONFLICT";
	public static final String UPDATE_CONFLICT = "UPDATE_CONFLICT";
	public static final String DELETE_CONFLICT = "DELETE_CONFLICT";

	/* Security */
	public static final String DISABLE_ACCOUNT = "DISABLE_ACCOUNT";
	public static final String LOCKED_ACCOUNT = "LOCKED_ACCOUNT";
	public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
	public static final String PASSWORD_NOT_HASH = "PASSWORD_NOT_HASH";
	public static final String SAME_PASSWORD = "SAME_PASSWORD";
	public static final String TOKEN_BLOCKED = "TOKEN_BLOCKED";
	public static final String AUTHENTICATION_ERROR = "AUTHENTICATION_ERROR";

}
