package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.olympiad.models.OpiDates;
import br.edu.opi.manager.olympiad.repositories.OpiDatesRepository;
import br.edu.opi.manager.project_patterns.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class OpiDatesService extends GenericService<Long, OpiDates, OpiDatesRepository> {

	public OpiDates show() {
		return this.show(OpiDates.OPI_DATES_ID);
	}

	public OpiDates update(OpiDates model) {
		validateBeforeUpdate(model);
		return this.update(OpiDates.OPI_DATES_ID, model);
	}

	@Override
	public void validateBeforeCreate(OpiDates model) {

	}

	@Override
	public void validateBeforeUpdate(OpiDates model) {

	}

	@Override
	public void validateBeforeDelete(Long aLong) {

	}

}
