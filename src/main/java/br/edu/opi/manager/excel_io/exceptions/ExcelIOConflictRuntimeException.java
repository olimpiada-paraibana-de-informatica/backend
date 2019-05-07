package br.edu.opi.manager.excel_io.exceptions;

import br.edu.opi.manager.project_patterns.exceptions.ExceptionWithErrorCode;

public abstract class ExcelIOConflictRuntimeException extends RuntimeException implements ExceptionWithErrorCode {

	public ExcelIOConflictRuntimeException(String message) {
		super(message);
	}

}
