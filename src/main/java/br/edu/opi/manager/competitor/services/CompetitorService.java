package br.edu.opi.manager.competitor.services;

import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import br.edu.opi.manager.excel_io.models.StudentTableRow;
import br.edu.opi.manager.project_patterns.exceptions.NotFoundRuntimeException;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.school.models.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitorService extends GenericService<Long, Competitor, CompetitorRepository> {

	private CompetitorRepository competitorRepository;

	public CompetitorService(CompetitorRepository competitorRepository) {
		this.competitorRepository = competitorRepository;
	}

	@Override
	public void validateBeforeCreate(Competitor competitor) {
	}

	@Override
	public void validateBeforeUpdate(Competitor competitor) {
	}

	@Override
	public void validateBeforeDelete(Long id) {
	}

}
