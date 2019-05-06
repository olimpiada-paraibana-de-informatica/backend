package br.edu.opi.manager.comunication.email.services;

import br.edu.opi.manager.comunication.email.models.InterestedNewDelegates;
import br.edu.opi.manager.comunication.email.repositories.InterestedNewDelegatesRepository;
import br.edu.opi.manager.project_patterns.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class InterestedNewDelegatesService extends GenericService<Long, InterestedNewDelegates, InterestedNewDelegatesRepository> {

	@Override
	public void validateBeforeCreate(InterestedNewDelegates model) {
	}

	@Override
	public void validateBeforeUpdate(InterestedNewDelegates model) {
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}

}
