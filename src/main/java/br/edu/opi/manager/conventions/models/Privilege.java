package br.edu.opi.manager.conventions.models;

import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

/**
 * The main idea is to create a key prefix according to CISUD, ie: <br>
 * - C_ to Create resource;<br>
 * - I_ to index (list) resource;<br>
 * - S_ to show (get by id) resource;<br>
 * - U_ to Update feature;<br>
 * - D_ to Delete feature.<br>
 * <p>
 * For the component UserModel, use US sufix.
 */
@ApiIgnore
public enum Privilege implements Serializable {

	// @formatter:off
	C_US(PrivilegeUtils.C_US, "CREATE_USER", "Criar Usuário", PrivilegeUtils.US),
	I_US(PrivilegeUtils.I_US, "INDEX_USER", "Visualizar todos os Usuários", PrivilegeUtils.US),
	S_US(PrivilegeUtils.S_US, "SHOW_USER", "Detalhes de Usuário", PrivilegeUtils.US),
	U_US(PrivilegeUtils.U_US, "UPDATE_USER", "Editar Usuário", PrivilegeUtils.US),
	D_US(PrivilegeUtils.D_US, "DELETE_USER", "Deletar Usuário", PrivilegeUtils.US),
	R_US(PrivilegeUtils.R_US, "RESET_USER_PASSWORD", "Resetar senha de Usuário", PrivilegeUtils.US),
	P_US(PrivilegeUtils.P_US, "CHANGE_USER_PROFILE", "Modificar perfil de Usuário", PrivilegeUtils.US),
	C_DE(PrivilegeUtils.C_DE, "CREATE_DELEGATE", "Criar Delegado", PrivilegeUtils.DE),
	I_DE(PrivilegeUtils.I_DE, "INDEX_DELEGATE", "Visualizar todos os Delegados", PrivilegeUtils.DE),
	S_DE(PrivilegeUtils.S_DE, "SHOW_DELEGATE", "Detalhes de Delegados", PrivilegeUtils.DE),
	U_DE(PrivilegeUtils.U_DE, "UPDATE_DELEGATE", "Editar Delegado", PrivilegeUtils.DE),
	D_DE(PrivilegeUtils.D_DE, "DELETE_DELEGATE", "Deletar Delegados", PrivilegeUtils.DE),

	;
	// @formatter:on

	private static final long serialVersionUID = 8974689912995859383L;

	// UserModel
	public static final String CREATE_USER = PrivilegeUtils.C_US;
	public static final String INDEX_USER = PrivilegeUtils.I_US;
	public static final String SHOW_USER = PrivilegeUtils.S_US;
	public static final String UPDATE_USER = PrivilegeUtils.U_US;
	public static final String DELETE_USER = PrivilegeUtils.D_US;
	public static final String RESET_USER_PASSWORD = PrivilegeUtils.R_US;
	public static final String CHANGE_USER_PROFILE = PrivilegeUtils.P_US;

	//Delegate
	public static final String CREATE_DELEGATE = PrivilegeUtils.C_DE;
	public static final String INDEX_DELEGATE = PrivilegeUtils.I_DE;
	public static final String SHOW_DELEGATE = PrivilegeUtils.S_DE;
	public static final String UPDATE_DELEGATE = PrivilegeUtils.U_DE;
	public static final String DELETE_DELEGATE = PrivilegeUtils.D_DE;

	private String key;
	private String name;
	private String nameFriendly;
	private String category;

	Privilege(String key, String name, String nameFriendly, String category) {
		this.key = key;
		this.name = name;
		this.nameFriendly = nameFriendly;
		this.category = category;
	}

	public String getKey() {
		return key;
	}

	public String getNameFriendly() {
		return nameFriendly;
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

}

class PrivilegeUtils {

	static final String US = "Usuário";
	static final String DE = "Delegado";

	// UserModel
	static final String C_US = "C_US";
	static final String I_US = "I_US";
	static final String S_US = "S_US";
	static final String U_US = "U_US";
	static final String D_US = "D_US";
	static final String R_US = "R_US";
	static final String P_US = "P_US";

	//Delegate
	static final String C_DE = "C_DE";
	static final String I_DE = "I_DE";
	static final String S_DE = "S_DE";
	static final String U_DE = "U_DE";
	static final String D_DE = "D_DE";
}
