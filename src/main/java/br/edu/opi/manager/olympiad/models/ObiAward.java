package br.edu.opi.manager.olympiad.models;

public enum ObiAward {
	GOLD("GOLD", "Ouro"),
	SILVER("SILVER", "Prata"),
	BRONZE("BRONZE", "Bronze"),
	HONORABLE_MENTION("HONORABLE_MENTION", "Menção Honrosa"),
	PARTICIPATION("PARTICIPATION", "Participação");

	private String key;
	private String name;

	ObiAward(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public static ObiAward from(String string) {
		for (ObiAward award : ObiAward.values()) {
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
