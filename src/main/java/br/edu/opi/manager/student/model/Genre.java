package br.edu.opi.manager.student.model;

public enum Genre {
	MASCULINO("MASCULINO", "Masculino"),
	FEMININO("FEMININO", "Feminino"),
	NÃO_BINÁRIO("NÃO_BINÁRIO", "Não Binário");

	private String key;
	private String name;

	private Genre(String key, String name) {
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
		return key;
	}

}
