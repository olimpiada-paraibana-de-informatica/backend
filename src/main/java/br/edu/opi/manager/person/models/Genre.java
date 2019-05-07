package br.edu.opi.manager.person.models;

public enum Genre {
	MASCULINO("MASCULINO", "Masculino"),
	FEMININO("FEMININO", "Feminino"),
	NÃO_BINÁRIO("NÃO_BINÁRIO", "Não Binário");

	private String key;
	private String name;

	Genre(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public static Genre from(String string) {
		for (Genre genre : Genre.values()) {
			if (genre.name.equalsIgnoreCase(string)) {
				return genre;
			}
		}
		throw new RuntimeException("Genre '" + string + "' don't exists");
	}

	@Override
	public String toString() {
		return key;
	}

}
