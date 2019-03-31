package br.edu.opi.manager.olympiad.model;

/**
 * Category of the OPI, namely: <br>
 * - Iniciação 1 <br>
 * - Iniciação 1 Escolas Públicas <br>
 * - Iniciação 2 <br>
 * - Iniciação 2 Escolas Públicas <br>
 * - Programação <br>
 * - Avançado Júnior <br>
 * - Avançado Sênior <br>
 */
public enum OpiCategory {
	Iniciação_1("Iniciação_1", "Iniciação 1"),
	Iniciação_1_Pub("Iniciação_1_Pub", "Iniciação 1 Escolas Públicas"),
	Iniciação_2("Iniciação_2", "Iniciação 2"),
	Iniciação_2_Pub("Iniciação_2_Pub", "Iniciação 2 Escolas Públicas"),
	Programação("Programação", "Programação"),
	Avançado_Júnior("Avançado_Júnior", "Avançado Júnior"),
	Avançado_Sênior("Avançado_Sênior", "Avançado Sênior");

	private String key;
	private String name;

	private OpiCategory(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
