package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import br.edu.opi.manager.olympiad.models.OpiCategory;
import br.edu.opi.manager.school.models.School;
import br.edu.opi.manager.school.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class OpiLevelService {

	private SchoolRepository schoolRepository;
	private CompetitorRepository competitorRepository;

	@Autowired
	public OpiLevelService(
			SchoolRepository schoolRepository,
			CompetitorRepository competitorRepository) {
		this.schoolRepository = schoolRepository;
		this.competitorRepository = competitorRepository;
	}

	public void levelTwoClassifier(Integer percentageConsidered) {
		Set<School> schools = new HashSet<>(schoolRepository.findAll());
		if (schools == null || schools.isEmpty()) {
			return;
		}
		for (School school : schools) {
			for (OpiCategory category : school.getCategories()) {
				levelTwoClassifier(school.getId(), category, percentageConsidered);
			}
		}
	}

	private void levelTwoClassifier(Long schoolId, OpiCategory category, Integer percentageConsidered) {
		LinkedHashSet<Competitor> competitors = new LinkedHashSet<>(
				competitorRepository.findAllByStudentSchoolIdAndCategoryAndYear(
						schoolId,
						category,
						LocalDate.now().getYear(),
						Sort.by(Sort.Order.desc("scoreLevelOne"))));
		if (competitors == null || competitors.isEmpty()) {
			return;
		}
		int total = competitors.size();
		int totalLevelTwo = (int) Math.ceil(total * percentageConsidered / 100.0);
		double lastScore = 0.0;
		long limit = 0;
		for (Competitor competitor : competitors) {
			if (limit++ < totalLevelTwo) {
				if (competitor.getScoreLevelOne().compareTo(0.0) > 0) {
					competitor.upLevelTwo();
					lastScore = competitor.getScoreLevelOne();
				} else {
					competitor.downLevelOne();
				}
			} else {
				if (competitor.getScoreLevelOne().compareTo(lastScore) == 0) {
					competitor.upLevelTwo();
				} else {
					competitor.downLevelOne();
				}
			}
		}
		competitorRepository.saveAll(competitors); // TODO: improve this $@#$&*%*&
	}

}
