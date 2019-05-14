package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import br.edu.opi.manager.olympiad.models.OpiLevels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Service
public class OpiLevelService {

	private CompetitorRepository competitorRepository;

	@Autowired
	public OpiLevelService(CompetitorRepository competitorRepository) {
		this.competitorRepository = competitorRepository;
	}

	public void levelTwoClassifier(Integer percentageConsidered) {
		Set<Competitor> classifieds = competitorRepository.findAllClassifieds(LocalDate.now().getYear(), percentageConsidered);
		for (Competitor competitor : classifieds) {
			competitor.upLevelTwo();
//			competitor.setLevel(OpiLevels.TWO);
		}
		// TODO: improve this $@#$&*%*&
		competitorRepository.saveAll(classifieds);
	}

}
