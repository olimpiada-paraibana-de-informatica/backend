package br.edu.opi.manager.person.services;

import br.edu.opi.manager.person.exceptions.ItsNotFullNameRuntimeException;
import br.edu.opi.manager.person.exceptions.LastNameCantAbbreviatedRuntimeException;
import br.edu.opi.manager.person.models.PartsPersonName;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {

	// TODO:
	public PartsPersonName processName(String fullName) {
		PartsPersonName partsPersonName = new PartsPersonName();
		String[] parts = fullName.trim().split(" ");
		if (parts.length < 2) {
			throw new ItsNotFullNameRuntimeException();
		}
		String firstName = parts[0];
		if (isAbbreviatedName(firstName)) {
			throw new LastNameCantAbbreviatedRuntimeException();
		}
		partsPersonName.setFirstName(firstName.toUpperCase());
		String lastName = parts[parts.length - 1];
		if (isAbbreviatedName(lastName)) {
			throw new LastNameCantAbbreviatedRuntimeException();
		}
		partsPersonName.setLastName(lastName.toUpperCase());
		String acronym = "";
		for (String part : parts) {
			if (!isPrepositionOfName(part)) {
				acronym += String.valueOf(part.charAt(0));
			}
		}
		partsPersonName.setAcronym(acronym.toUpperCase());
		return partsPersonName;
	}

	private boolean isPrepositionOfName(String partName) {
		return prepositionsOfName.contains(partName.toLowerCase());
	}

	private boolean isAbbreviatedName(String partName) {
		return partName.length() < 2;
	}

	// TODO: put in a table and CRUD
	final List<String> prepositionsOfName = Arrays.asList("a", "à", "á", "e", "é", "o", "ó", "u", "ú", "da", "dá", "das", "dás", "de", "des", "dé", "dés", "do", "dó", "dos", "dós");

}
