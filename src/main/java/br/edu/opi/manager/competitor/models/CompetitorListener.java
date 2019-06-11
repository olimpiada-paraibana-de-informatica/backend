package br.edu.opi.manager.competitor.models;

import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import br.edu.opi.manager.olympiad.models.OpiCategory;
import br.edu.opi.manager.school.models.Grade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CompetitorListener {

	@Autowired
	CompetitorRepository competitorRepository;

	@PrePersist
	@PreUpdate
	public void executeBeforeSave(Competitor competitor) {
		OpiCategory category = solveCategory(competitor);
//		OpiAward award = solveAward(competitor);
		competitor.setCategory(category);
	}

	private OpiCategory solveCategory(Competitor competitor) {
		Grade grade = competitor.getGrade();
		boolean isPublic = competitor.isSchoolPublic();
		OpiCategory category = null;
		switch (grade) {
			case FIRST_ELEMENTARY:
			case SECOND_ELEMENTARY:
			case THIRD_ELEMENTARY:
			case FOURTH_ELEMENTARY:
				if (isPublic) {
					category = OpiCategory.INICIACAO_1_PUB;
				} else {
					category = OpiCategory.INICIACAO_1;
				}
				break;
			case FIFTH_ELEMENTARY:
			case SIXTH_ELEMENTARY:
			case SEVENTH_ELEMENTARY:
			case EIGHTH_ELEMENTARY:
				if (isPublic) {
					category = OpiCategory.INICIACAO_2_PUB;
				} else {
					category = OpiCategory.INICIACAO_2;
				}
				break;
			case NINTH_ELEMENTARY:
			case FIRST_HIGH:
			case SECOND_HIGH:
			case THIRD_HIGH:
				category = OpiCategory.PROGRAMACAO;
				break;
			default:
				// TODO: handle HIGHER
				category = OpiCategory.AVANCADO_JUNIOR;
		}
		return category;
	}

	// TODO: talking with Rohit about
	/*private OpiAward solveAward(Competitor competitor) {
		OpiAward award = competitor.getAward();
		Double scoreLevelOne = competitor.getScoreLevelOne();
		Double scoreLevelTwo = competitor.getScoreLevelTwo();
		if (scoreLevelOne.compareTo(-1D) == 0 || scoreLevelTwo.compareTo(-1D) == 0) {
			award = OpiAward.ABSENCE;
		}
		return award;
	}*/

}
