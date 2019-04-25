package br.edu.opi.manager.project_patterns.models.user;

import java.util.HashSet;
import java.util.Set;

public class ProfileFactory {

	public static Profile delegateUser() {
		Set<Privilege> delegatePrivileges = new HashSet<Privilege>() {
			private static final long serialVersionUID = 2249152407872739555L;
			{
				// TODO: change to Delegate own privileges
				add(Privilege.S_US);
				add(Privilege.U_US);
				add(Privilege.SA_SC);
				add(Privilege.UA_SC);
				add(Privilege.CA_ST);
				add(Privilege.UA_ST);
				add(Privilege.IA_ST);
				add(Privilege.SA_ST);
				add(Privilege.DA_ST);
				add(Privilege.CA_CO);
			}
		};
		return new Profile(1L, "Delegado", delegatePrivileges);
	}

}
