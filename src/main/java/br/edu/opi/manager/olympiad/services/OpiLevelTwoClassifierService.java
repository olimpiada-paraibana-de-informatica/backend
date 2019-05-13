package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OpiLevelTwoClassifierService {

	private CompetitorRepository competitorRepository;

	@Autowired
	public OpiLevelTwoClassifierService(CompetitorRepository competitorRepository) {
		this.competitorRepository = competitorRepository;
	}

	public void levelTwoClassifier(Double percentageConsidered) {
		// TODO: call plpgsql function and be happy
	}

}
