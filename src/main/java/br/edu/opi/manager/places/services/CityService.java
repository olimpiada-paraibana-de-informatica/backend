package br.edu.opi.manager.places.services;

import br.edu.opi.manager.places.models.City;
import br.edu.opi.manager.places.repositories.CityRepository;
import br.edu.opi.manager.project_patterns.services.GenericService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService extends GenericService<Long, City, CityRepository> {

	public List<City> index(String namePartial, String stateAbbreviation, Long stateCbo) {
		if (namePartial != null && !namePartial.isEmpty() && stateAbbreviation != null && !stateAbbreviation.isEmpty()) {
			return repository.findFirst10ByNameContainingIgnoreCaseAndUfAbbreviationIgnoreCase(namePartial, stateAbbreviation);
		}
		if (namePartial != null && !namePartial.isEmpty()) {
			return repository.findFirst10ByNameContainingIgnoreCase(namePartial);
		}
		if (stateAbbreviation != null && !stateAbbreviation.isEmpty()) {
			return repository.findByUfAbbreviationIgnoreCase(stateAbbreviation);
		}
		if (stateCbo != null && stateCbo != 0) {
			return repository.findByUfCboCode(stateCbo);
		}
		return new ArrayList<>();
	}

	@Override
	public void validateBeforeCreate(City model) {
	}

	@Override
	public void validateBeforeUpdate(City model) {
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}
}
