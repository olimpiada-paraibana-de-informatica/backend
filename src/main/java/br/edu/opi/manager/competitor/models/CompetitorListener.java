package br.edu.opi.manager.competitor.models;

import br.edu.opi.manager.olympiad.models.OpiCategory;
import br.edu.opi.manager.school.models.Grade;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CompetitorListener {

	@PrePersist
	@PreUpdate
	public void executeBeforeSave(Competitor competitor) {
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
		competitor.setCategory(category);
	}

}
