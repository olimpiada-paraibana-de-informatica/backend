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
	INICIACAO_1("INICIACAO_1", "Iniciação 1", 2015),
	INICIACAO_1_PUB("INICIACAO_1_PUB", "Iniciação 1 Escolas Públicas", 2017),
	INICIACAO_2("INICIACAO_2", "Iniciação 2", 2008),
	INICIACAO_2_PUB("INICIACAO_2_PUB", "Iniciação 2 Escolas Públicas", 2013),
	PROGRAMACAO("PROGRAMACAO", "Programação", 2008),
	AVANCADO_JUNIOR("AVANCADO_JUNIOR", "Avançado Júnior", 2010),
	AVANCADO_SENIOR("AVANCADO_SENIOR", "Avançado Sênior", 2010);

	private String key;
	private String name;
	private Integer beginYear;

	private OpiCategory(String key, String name, Integer beginYear) {
		this.key = key;
		this.name = name;
		this.beginYear = beginYear;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public Integer getBeginYear() {
		return beginYear;
	}

	public static OpiCategory from(String string) {
		for (OpiCategory category : OpiCategory.values()) {
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
