package br.edu.opi.manager.exceptions;

//import br.edu.ufcg.utils.database.DatabaseConstants;

public class DataManipulationRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 28740502921097237L;

	public DataManipulationRuntimeException(final String cause) {
//		super(DatabaseConstants.recoveryCreateFriendlyMessage(cause));
	}

}
