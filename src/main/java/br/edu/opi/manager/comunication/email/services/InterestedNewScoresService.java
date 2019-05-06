package br.edu.opi.manager.comunication.email.services;

import br.edu.opi.manager.comunication.email.models.InterestedNewScores;
import br.edu.opi.manager.comunication.email.repositories.InterestedNewScoresRepository;
import br.edu.opi.manager.project_patterns.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class InterestedNewScoresService extends GenericService<Long, InterestedNewScores, InterestedNewScoresRepository> {

	@Override
	public void validateBeforeCreate(InterestedNewScores model) {
	}

	@Override
	public void validateBeforeUpdate(InterestedNewScores model) {
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}

}
