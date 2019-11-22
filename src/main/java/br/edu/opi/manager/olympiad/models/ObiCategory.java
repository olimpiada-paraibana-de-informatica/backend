package br.edu.opi.manager.olympiad.models;

/**
 * Category of the OBI, namely: <br>
 * - Iniciação Júnior <br>
 * - Iniciação 1 <br>
 * - Iniciação 2 <br>
 * - Programação Júnior <br>
 * - Programação 1 <br>
 * - Programação 2 <br>
 * - Programação Sênior <br>
 */
public enum ObiCategory {
	INICIACAO_JUNIOR("INICIACAO_JUNIOR", "Iniciação Júnior"),
	INICIACAO_1("INICIACAO_1", "Iniciação 1"),
	INICIACAO_2("INICIACAO_2", "Iniciação 2"),
	PROGRAMACAO_JUNIOR("PROGRAMACAO_JUNIOR", "Programação Júnior"),
	PROGRAMACAO_1("PROGRAMACAO_1", "Programação 1"),
	PROGRAMACAO_2("PROGRAMACAO_2", "Programação 2"),
	PROGRAMACAO_SENIOR("PROGRAMACAO_SENIOR", "Programação Sênior");

	private String key;
	private String name;

	private ObiCategory(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public static ObiCategory from(String string) {
		for (ObiCategory category : ObiCategory.values()) {
			if (category.name.equalsIgnoreCase(string)) {
				return category;
			}
		}
		throw new RuntimeException("Category '" + string + "' don't exists");
	}

	@Override
	public String toString() {
		return key;
	}

}
