package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.olympiad.models.InternationalChampion;
import br.edu.opi.manager.olympiad.repositories.InternationalChampionRepository;
import br.edu.opi.manager.project_patterns.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternationalChampionService extends GenericService<Long, InternationalChampion, InternationalChampionRepository> {

	private InternationalChampionRepository internationalChampionRepository;

	@Autowired
	public InternationalChampionService(InternationalChampionRepository internationalChampionRepository) {
		this.internationalChampionRepository = internationalChampionRepository;
	}

	public List<InternationalChampion> indexByOrderByYear() {
		return internationalChampionRepository.findAll();
	}

	@Override
	public void validateBeforeCreate(InternationalChampion model) {
	}

	@Override
	public void validateBeforeUpdate(InternationalChampion model) {
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}

}
