package br.edu.opi.manager.project_patterns.models.user;

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
	//User
	C_US(PrivilegeUtils.C_US, "CREATE_USER", "Criar Usuário", PrivilegeUtils.US),
	I_US(PrivilegeUtils.I_US, "INDEX_USER", "Visualizar todos os Usuários", PrivilegeUtils.US),
	S_US(PrivilegeUtils.S_US, "SHOW_USER", "Detalhes de Usuário", PrivilegeUtils.US),
	U_US(PrivilegeUtils.U_US, "UPDATE_USER", "Editar Usuário", PrivilegeUtils.US),
	D_US(PrivilegeUtils.D_US, "DELETE_USER", "Deletar Usuário", PrivilegeUtils.US),
	R_US(PrivilegeUtils.R_US, "RESET_USER_PASSWORD", "Resetar senha de Usuário", PrivilegeUtils.US),
	P_US(PrivilegeUtils.P_US, "CHANGE_USER_PROFILE", "Modificar perfil de Usuário", PrivilegeUtils.US),
	C_DE(PrivilegeUtils.C_DE, "CREATE_DELEGATE", "Criar Delegado", PrivilegeUtils.DE),
	//Delegate
	I_DE(PrivilegeUtils.I_DE, "INDEX_DELEGATE", "Visualizar todos os Delegados", PrivilegeUtils.DE),
	S_DE(PrivilegeUtils.S_DE, "SHOW_DELEGATE", "Detalhes de Delegados", PrivilegeUtils.DE),
	U_DE(PrivilegeUtils.U_DE, "UPDATE_DELEGATE", "Editar Delegado", PrivilegeUtils.DE),
	D_DE(PrivilegeUtils.D_DE, "DELETE_DELEGATE", "Deletar Delegados", PrivilegeUtils.DE),
	D_LN(PrivilegeUtils.D_LN, "DELETE_LEVEL_NOTICE", "Avisar preenchimento das notas de cada fase", PrivilegeUtils.DE),
	//School
	C_SC(PrivilegeUtils.C_SC, "CREATE_SCHOOL", "Criar Escola a qualquer momento", PrivilegeUtils.SC),
	I_SC(PrivilegeUtils.I_SC, "INDEX_SCHOOL", "Visualizar todas as Escolas", PrivilegeUtils.SC),
	S_SC(PrivilegeUtils.S_SC, "SHOW_SCHOOL", "Detalhes da Escola", PrivilegeUtils.SC),
	U_SC(PrivilegeUtils.U_SC, "UPDATE_SCHOOL", "Editar Escola", PrivilegeUtils.SC),
	D_SC(PrivilegeUtils.D_SC, "DELETE_SCHOOL", "Deletar Escola", PrivilegeUtils.SC),
	SA_SC(PrivilegeUtils.SA_SC, "SHOW_ASSOCIATED_SCHOOL", "Detalhes da Escola associada", PrivilegeUtils.SC),
	UA_SC(PrivilegeUtils.UA_SC, "UPDATE_ASSOCIATED_SCHOOL", "Atualizar Escola associada", PrivilegeUtils.SC),
	//Student
	C_ST(PrivilegeUtils.C_ST, "CREATE_STUDENT", "Criar estudante", PrivilegeUtils.ST),
	I_ST(PrivilegeUtils.I_ST, "INDEX_STUDENT", "Visualizar todos os Estudantes", PrivilegeUtils.ST),
	S_ST(PrivilegeUtils.S_ST, "SHOW_STUDENT", "Detalhes do Estudante", PrivilegeUtils.ST),
	U_ST(PrivilegeUtils.U_ST, "UPDATE_STUDENT", "Editar Estudante", PrivilegeUtils.ST),
	D_ST(PrivilegeUtils.D_ST, "DELETE_STUDENT", "Deletar Estudante", PrivilegeUtils.ST),
	CA_ST(PrivilegeUtils.CA_ST, "CREATE_ASSOCIATED_STUDENT", "Criar estudante associado", PrivilegeUtils.ST),
	IA_ST(PrivilegeUtils.IA_ST, "INDEX_ASSOCIATED_STUDENT", "Visualizar todos os Estudantes associados", PrivilegeUtils.ST),
	SA_ST(PrivilegeUtils.SA_ST, "SHOW_ASSOCIATED_STUDENT", "Detalhes do Estudante associado", PrivilegeUtils.ST),
	UA_ST(PrivilegeUtils.UA_ST, "UPDATE_ASSOCIATED_STUDENT", "Editar Estudante associado", PrivilegeUtils.ST),
	DA_ST(PrivilegeUtils.DA_ST, "DELETE_ASSOCIATED_STUDENT", "Deletar Estudante associado", PrivilegeUtils.ST),
	//Competitor
	C_CO(PrivilegeUtils.C_CO, "CREATE_COMPETITOR", "Criar Competidor", PrivilegeUtils.CO),
	I_CO(PrivilegeUtils.I_CO, "INDEX_COMPETITOR", "Visualizar todos os Competidores", PrivilegeUtils.CO),
	S_CO(PrivilegeUtils.S_CO, "SHOW_COMPETITOR", "Detalhes do Competidor", PrivilegeUtils.CO),
	U_CO(PrivilegeUtils.U_CO, "UPDATE_COMPETITOR", "Editar Competidor", PrivilegeUtils.CO),
	D_CO(PrivilegeUtils.D_CO, "DELETE_COMPETITOR", "Deletar Competidor", PrivilegeUtils.CO),
	CA_CO(PrivilegeUtils.CA_CO, "CREATE_ASSOCIATED_COMPETITOR", "Criar Competidor associado", PrivilegeUtils.CO),
	IA_CO(PrivilegeUtils.IA_CO, "INDEX_ASSOCIATED_COMPETITOR", "Visualizar todos os Competidores associados", PrivilegeUtils.CO),
	SA_CO(PrivilegeUtils.SA_CO, "SHOW_ASSOCIATED_COMPETITOR", "Detalhes do Competidor associado", PrivilegeUtils.CO),
	UA_CO(PrivilegeUtils.UA_CO, "UPDATE_ASSOCIATED_COMPETITOR", "Editar Competidor associado", PrivilegeUtils.CO),
	DA_CO(PrivilegeUtils.DA_CO, "DELETE_ASSOCIATED_COMPETITOR", "Deletar Competidor associado", PrivilegeUtils.CO),
	//Interested
	I_EM(PrivilegeUtils.I_EM, "INDEX_EMAILS_LIST", "Visualizar todas as listas de e-mails", PrivilegeUtils.EM),
	S_EM(PrivilegeUtils.S_EM, "SHOW_EMAILS_LIST", "Detalhes de uma lista de e-mails", PrivilegeUtils.EM),
	C_EM(PrivilegeUtils.C_EM, "CREATE_EMAILS_LIST", "Criar lista de e-mails", PrivilegeUtils.EM),
	U_EM(PrivilegeUtils.U_EM, "UPDATE_EMAILS_LIST", "Atualizar lista de e-mails", PrivilegeUtils.EM),
	D_EM(PrivilegeUtils.D_EM, "DELETE_EMAILS_LIST", "Deletar listas de e-mails", PrivilegeUtils.EM),
	//OPI
	U_OL(PrivilegeUtils.U_OL, "UPDATE_OPI_LEVEL", "Definir fase dos Competidores", PrivilegeUtils.OPI),
	//HTML
	I_HT(PrivilegeUtils.I_HT, "INDEX_HTML", "Visualizar todos os fragmentos HTML", PrivilegeUtils.HTML),
	S_HT(PrivilegeUtils.S_HT, "SHOW_HTML", "Detalhes de um fragmento HTML", PrivilegeUtils.HTML),
	C_HT(PrivilegeUtils.C_HT, "CREATE_HTML", "Criar um fragmento HTML", PrivilegeUtils.HTML),
	U_HT(PrivilegeUtils.U_HT, "UPDATE_HTML", "Atualizar um fragmento HTML", PrivilegeUtils.HTML),
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
	public static final String LEVEL_NOTICE = PrivilegeUtils.D_LN;

	//School
	public static final String CREATE_SCHOOL = PrivilegeUtils.C_SC;
	public static final String INDEX_SCHOOL = PrivilegeUtils.I_SC;
	public static final String SHOW_SCHOOL = PrivilegeUtils.S_SC;
	public static final String UPDATE_SCHOOL = PrivilegeUtils.U_SC;
	public static final String DELETE_SCHOOL = PrivilegeUtils.D_SC;
	public static final String SHOW_ASSOCIATED_SCHOOL = PrivilegeUtils.SA_SC;
	public static final String UPDATE_ASSOCIATED_SCHOOL = PrivilegeUtils.UA_SC;

	//Student
	public static final String CREATE_STUDENT = PrivilegeUtils.C_ST;
	public static final String INDEX_STUDENT = PrivilegeUtils.I_ST;
	public static final String SHOW_STUDENT = PrivilegeUtils.S_ST;
	public static final String UPDATE_STUDENT = PrivilegeUtils.U_ST;
	public static final String DELETE_STUDENT = PrivilegeUtils.D_ST;
	public static final String CREATE_ASSOCIATED_STUDENT = PrivilegeUtils.CA_ST;
	public static final String INDEX_ASSOCIATED_STUDENT = PrivilegeUtils.IA_ST;
	public static final String SHOW_ASSOCIATED_STUDENT = PrivilegeUtils.SA_ST;
	public static final String UPDATE_ASSOCIATED_STUDENT = PrivilegeUtils.UA_ST;
	public static final String DELETE_ASSOCIATED_STUDENT = PrivilegeUtils.DA_ST;

	//Competitor
	public static final String CREATE_COMPETITOR = PrivilegeUtils.C_CO;
	public static final String INDEX_COMPETITOR = PrivilegeUtils.I_CO;
	public static final String SHOW_COMPETITOR = PrivilegeUtils.S_CO;
	public static final String UPDATE_COMPETITOR = PrivilegeUtils.U_CO;
	public static final String DELETE_COMPETITOR = PrivilegeUtils.D_CO;
	public static final String CREATE_ASSOCIATED_COMPETITOR = PrivilegeUtils.CA_CO;
	public static final String INDEX_ASSOCIATED_COMPETITOR = PrivilegeUtils.IA_CO;
	public static final String SHOW_ASSOCIATED_COMPETITOR = PrivilegeUtils.SA_CO;
	public static final String UPDATE_ASSOCIATED_COMPETITOR = PrivilegeUtils.UA_CO;
	public static final String DELETE_ASSOCIATED_COMPETITOR = PrivilegeUtils.DA_CO;

	// Interested
	public static final String INDEX_EMAILS_LIST = PrivilegeUtils.I_EM;
	public static final String SHOW_EMAILS_LIST = PrivilegeUtils.S_EM;
	public static final String CREATE_EMAILS_LIST = PrivilegeUtils.C_EM;
	public static final String UPDATE_EMAILS_LIST = PrivilegeUtils.U_EM;
	public static final String DELETE_EMAILS_LIST = PrivilegeUtils.D_EM;

	// OPI
	public static final String INDEX_OPI_LEVEL = PrivilegeUtils.I_OL;
	public static final String UPDATE_OPI_LEVEL = PrivilegeUtils.U_OL;

	// HTML
	public static final String INDEX_HTML = PrivilegeUtils.I_HT;
	public static final String SHOW_HTML = PrivilegeUtils.S_HT;
	public static final String CREATE_HTML = PrivilegeUtils.C_HT;
	public static final String UPDATE_HTML = PrivilegeUtils.U_HT;

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
	static final String SC = "Escola";
	static final String ST = "Estudante";
	static final String CO = "Competidor";
	static final String EM = "E-mails";
	public static final String OPI = "OPI";
	public static final String HTML = "HTML";

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
	static final String D_LN = "D_LN";

	//School
	static final String C_SC = "C_SC";
	static final String I_SC = "I_SC";
	static final String S_SC = "S_SC";
	static final String U_SC = "U_SC";
	static final String D_SC = "D_SC";
	static final String SA_SC = "SA_SC";
	static final String UA_SC = "UA_SC";

	//Student
	static final String C_ST = "C_ST";
	static final String I_ST = "I_ST";
	static final String S_ST = "S_ST";
	static final String U_ST = "U_ST";
	static final String D_ST = "D_ST";
	static final String CA_ST = "CA_ST";
	static final String IA_ST = "IA_ST";
	static final String SA_ST = "SA_ST";
	static final String UA_ST = "UA_ST";
	static final String DA_ST = "DA_ST";

	//Competitor
	static final String C_CO = "C_CO";
	static final String I_CO = "I_CO";
	static final String S_CO = "S_CO";
	static final String U_CO = "U_CO";
	static final String D_CO = "D_CO";
	static final String CA_CO = "CA_CO";
	static final String IA_CO = "IA_CO";
	static final String SA_CO = "SA_CO";
	static final String UA_CO = "UA_CO";
	static final String DA_CO = "DA_CO";

	//Interested
	static final String I_EM = "I_EM";
	static final String S_EM = "S_EM";
	static final String C_EM = "C_EM";
	static final String U_EM = "U_EM";
	static final String D_EM = "D_EM";

	//OPI
	static final String U_OL = "U_OL";
	static final String I_OL = "I_OL";

	//HTML
	static final String I_HT = "I_HT";
	static final String S_HT = "S_HT";
	static final String C_HT = "C_HT";
	static final String U_HT = "U_HT";

}
