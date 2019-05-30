package br.edu.opi.manager.olympiad.models;

public enum OpiLevels {
	ONE("ONE", "Primeira Fase"),
	TWO("TWO", "Segunda Fase");

	private String key;
	private String friendlyName;

	OpiLevels(String key, String friendlyName) {
		this.key = key;
		this.friendlyName = friendlyName;
	}

	public String getKey() {
		return key;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

}
