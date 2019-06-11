package br.edu.opi.manager.olympiad.models;

public enum OpiAward {
	GOLD("GOLD", "Ouro"),
	SILVER("SILVER", "Prata"),
	BRONZE("BRONZE", "Bronze"),
	HONORABLE_MENTION("HONORABLE_MENTION", "Menção Honrosa"),
	PARTICIPATION("PARTICIPATION", "Participação"),
	ABSENCE("ABSENCE", "Falta");

	private String key;
	private String name;

	private OpiAward(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public static OpiAward from(String string) {
		for (OpiAward award : OpiAward.values()) {
			if (award.name.equalsIgnoreCase(string)) {
				return award;
			}
		}
		throw new RuntimeException("Award '" + string + "' don't exists");
	}

	@Override
	public String toString() {
		return key;
	}

}
