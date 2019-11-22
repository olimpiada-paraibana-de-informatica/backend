package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.olympiad.models.ObiChampion;
import br.edu.opi.manager.olympiad.models.ObiGraph;
import br.edu.opi.manager.olympiad.models.ObiGraphPk;
import br.edu.opi.manager.olympiad.repositories.ObiGraphRepository;
import br.edu.opi.manager.project_patterns.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObiGraphService extends GenericService<ObiGraphPk, ObiGraph, ObiGraphRepository> {

	private ObiGraphRepository obiGraphRepository;

	@Autowired
	public ObiGraphService(ObiGraphRepository obiGraphRepository) {
		this.obiGraphRepository = obiGraphRepository;
	}

	public synchronized void addChampionToGraph(ObiChampion obiChampion) {
		ObiGraphPk obiGraphPk = new ObiGraphPk(obiChampion.getYear(), obiChampion.getObiCategory());
		ObiGraph obiGraph = new ObiGraph(obiChampion.getYear(), 0, 0, 0, 0, obiChampion.getObiCategory());
		Optional<ObiGraph> obiGraphOpt = obiGraphRepository.findById(obiGraphPk);
		if (obiGraphOpt.isPresent()) {
			obiGraph = obiGraphOpt.get();
		}
		switch (obiChampion.getObiAward()) {
			case GOLD:
				obiGraph.addTotalGold();
				break;
			case SILVER:
				obiGraph.addTotalSilver();
				break;
			case BRONZE:
				obiGraph.addTotalBronze();
				break;
			case HONORABLE_MENTION:
				obiGraph.addTotalHonorableMention();
				break;
		}
		obiGraphRepository.save(obiGraph);
	}

	@Override
	public void validateBeforeCreate(ObiGraph model) {
	}

	@Override
	public void validateBeforeUpdate(ObiGraph model) {
	}

	@Override
	public void validateBeforeDelete(ObiGraphPk obiGraphPk) {
	}

}
