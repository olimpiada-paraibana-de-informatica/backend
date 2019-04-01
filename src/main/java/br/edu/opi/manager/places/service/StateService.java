package br.edu.opi.manager.places.service;

import br.edu.opi.manager.places.model.State;
import br.edu.opi.manager.places.repository.StateRepository;
import br.edu.opi.manager.project_patterns.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class StateService extends GenericService<Long, State, StateRepository> {

	@Override
	public void validateBeforeCreate(State model) {
	}

	@Override
	public void validateBeforeUpdate(State model) {
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}

}
