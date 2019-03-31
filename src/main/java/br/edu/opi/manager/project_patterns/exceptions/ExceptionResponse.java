package br.edu.opi.manager.project_patterns.exceptions;

import java.time.Instant;

public class ExceptionResponse {

	private Instant timestamp;
	private String message;
	private String details;
	private String errorCode;

	public ExceptionResponse(Instant timestamp, String message, String details, String errorCode) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.errorCode = errorCode;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
