package br.edu.opi.manager.school.models;

public enum Grade {
	FIRST_ELEMENTARY("FIRST_ELEMENTARY", "1º ano do Ensino Fundamental"),
	SECOND_ELEMENTARY("SECOND_ELEMENTARY", "2º ano do Ensino Fundamental"),
	THIRD_ELEMENTARY("THIRD_ELEMENTARY", "3º ano do Ensino Fundamental"),
	FOURTH_ELEMENTARY("FOURTH_ELEMENTARY", "4º ano do Ensino Fundamental"),
	FIFTH_ELEMENTARY("FIFTH_ELEMENTARY", "5º ano do Ensino Fundamental"),
	SIXTH_ELEMENTARY("SIXTH_ELEMENTARY", "6º ano do Ensino Fundamental"),
	SEVENTH_ELEMENTARY("SEVENTH_ELEMENTARY", "7º ano do Ensino Fundamental"),
	EIGHTH_ELEMENTARY("EIGHTH_ELEMENTARY", "8º ano do Ensino Fundamental"),
	NINTH_ELEMENTARY("NINTH_ELEMENTARY", "9º ano do Ensino Fundamental"),

	FIRST_HIGH("FIRST_HIGH", "1º ano do Ensino Médio"),
	SECOND_HIGH("SECOND_HIGH", "2º ano do Ensino Médio"),
	THIRD_HIGH("THIRD_HIGH", "3º ano do Ensino Médio"),
	FOURTH_HIGH("FOURTH_HIGH", "4º ano do Ensino Médio"),

	FIRST_HIGHER("FIRST_HIGHER", "1º semestre do Ensino Superior"),
	SECOND_HIGHER("SECOND_HIGHER", "2º semestre do Ensino Superior"),
	THIRD_HIGHER("THIRD_HIGHER", "3º semestre do Ensino Superior"),
	FOURTH_HIGHER("FOURTH_HIGHER", "4º semestre do Ensino Superior"),
	FIFTH_HIGHER("FIFTH_HIGHER", "5º semestre do Ensino Superior"),
	SIXTH_HIGHER("SIXTH_HIGHER", "6º semestre do Ensino Superior"),
	SEVENTH_HIGHER("SEVENTH_HIGHER", "7º semestre do Ensino Superior"),
	EIGHTH_HIGHER("EIGHTH_HIGHER", "8º semestre do Ensino Superior"),
	NINTH_HIGHER("NINTH_HIGHER", "9º semestre do Ensino Superior"),
	TENTH_HIGHER("TENTH_HIGHER", "10º semestre do Ensino Superior"),
	ELEVENTH_HIGHER("ELEVENTH_HIGHER", "11º semestre do Ensino Superior"),
	TWELFTH_HIGHER("TWELFTH_HIGHER", "12º semestre do Ensino Superior"),
	THIRTEENTH_HIGHER("THIRTEENTH_HIGHER", "13º semestre do Ensino Superior"),
	FOURTEENTH_HIGHER("FOURTEENTH_HIGHER", "14º semestre do Ensino Superior"),
	WHATEVER_HIGHER("WHATEVER_HIGHER", "Outro semestre do Ensino Superior ou Mestrado");

	private String key;
	private String name;

	Grade(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public static Grade from(String string) {
		for (Grade grade : Grade.values()) {
			if (grade.name.equalsIgnoreCase(string)) {
				return grade;
			}
		}
		throw new RuntimeException("Grade '" + string + "' don't exists");
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
