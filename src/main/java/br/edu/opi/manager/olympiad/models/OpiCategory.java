package br.edu.opi.manager.olympiad.models;

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
	INICIACAO_1("INICIACAO_1", "Iniciação 1"),
	INICIACAO_1_PUB("INICIACAO_1_PUB", "Iniciação 1 Escolas Públicas"),
	INICIACAO_2("INICIACAO_2", "Iniciação 2"),
	INICIACAO_2_PUB("INICIACAO_2_PUB", "Iniciação 2 Escolas Públicas"),
	PROGRAMACAO("PROGRAMACAO", "Programação"),
	AVANCADO_JUNIOR("AVANCADO_JUNIOR", "Avançado Júnior"),
	AVANCADO_SENIOR("AVANCADO_SENIOR", "Avançado Sênior");

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

	public static OpiCategory from(String string) {
		for (OpiCategory grade : OpiCategory.values()) {
			if (grade.name.equalsIgnoreCase(string)) {
				return grade;
			}
		}
		throw new RuntimeException("Category '" + string + "' don't exists");
	}

	@Override
	public String toString() {
		return key;
	}

}
