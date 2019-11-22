package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.olympiad.models.SbcGraph;
import br.edu.opi.manager.olympiad.repositories.SbcGraphRepository;
import br.edu.opi.manager.project_patterns.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class SbcGraphService extends GenericService<Integer, SbcGraph, SbcGraphRepository> {

	@Override
	public void validateBeforeCreate(SbcGraph model) {
	}

	@Override
	public void validateBeforeUpdate(SbcGraph model) {
	}

	@Override
	public void validateBeforeDelete(Integer integer) {
	}

}
