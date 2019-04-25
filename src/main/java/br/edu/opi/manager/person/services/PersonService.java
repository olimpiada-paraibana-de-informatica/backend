package br.edu.opi.manager.person.services;

import br.edu.opi.manager.person.exceptions.ItsNotFullNameRuntimeException;
import br.edu.opi.manager.person.exceptions.LastNameCantAbbreviatedRuntimeException;
import br.edu.opi.manager.person.models.PartsPersonName;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

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
		switch (partName) {
			// TODO: put in a table and CRUD
			case "a":
			case "e":
			case "o":
			case "u":
			case "da":
			case "dá":
			case "das":
			case "dás":
			case "de":
			case "des":
			case "dé":
			case "dés":
			case "do":
			case "dó":
			case "dos":
			case "dós":
				return true;
			default:
				return false;
		}
	}

	private boolean isAbbreviatedName(String partName) {
		return partName.length() < 2;
	}

}
