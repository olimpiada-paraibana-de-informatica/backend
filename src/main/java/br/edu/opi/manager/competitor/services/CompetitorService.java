package br.edu.opi.manager.competitor.services;

import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import br.edu.opi.manager.excel_io.models.CompetitorTableRow;
import br.edu.opi.manager.person.services.PersonService;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitorService extends GenericService<Long, Competitor, CompetitorRepository> {

	private StudentRepository studentRepository;

	private PersonService personService;

	@Autowired
	public CompetitorService(
			StudentRepository studentRepository,
			PersonService personService) {
		this.studentRepository = studentRepository;
		this.personService = personService;
	}

	public void solveAndSave(CompetitorTableRow competitorTableRow) {
		// TODO: change to competitor
//		PartsPersonName parts = personService.processName(competitorTableRow.getName());
//		Student savedStudent = studentRepository
//				.findByPersonAcronymAndPersonFirstNameAndPersonLastName(
//						parts.getAcronym(),
//						parts.getFirstName(),
//						parts.getLastName());
//		if (savedStudent == null) {
//			savedStudent = new Student(
//					new Person(
//							competitorTableRow.getName(),
//							competitorTableRow.getDateBirth(),
//							competitorTableRow.getGenre()),
//					competitorTableRow.getCompetitorTableMetadata().getSchool());
//		} else {
//			savedStudent.setSchool(competitorTableRow.getCompetitorTableMetadata().getSchool());
//		}
//		repository.save(savedStudent);
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
