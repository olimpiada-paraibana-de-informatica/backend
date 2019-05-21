package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class OpiLevelService {

	private CompetitorRepository competitorRepository;

	@Autowired
	public OpiLevelService(CompetitorRepository competitorRepository) {
		this.competitorRepository = competitorRepository;
	}

	public void levelTwoClassifier(Integer percentageConsidered) {
		List<Competitor> list = competitorRepository.findAllByYear(LocalDate.now().getYear(), Sort.by(Sort.Order.desc("scoreLevelOne")));
		LinkedHashSet<Competitor> competitors = new LinkedHashSet<>(list);
		int total = competitors.size();
		int totalLevelTwo = (int) Math.ceil(total * percentageConsidered / 100.0);
		competitors.stream().limit(totalLevelTwo).forEach(competitor -> {
			competitor.upLevelTwo();
//			competitor.setLevel(OpiLevels.TWO);
		});
		// TODO: while next last scores equal last, put level two too
		competitors.stream().skip(totalLevelTwo).forEach(competitor -> {
			competitor.downLevelOne();
		});
		// TODO: improve this $@#$&*%*&
		competitorRepository.saveAll(competitors);
	}

}
