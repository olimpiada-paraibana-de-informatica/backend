package br.edu.opi.manager.competitor.services;

import br.edu.opi.manager.competitor.exceptions.CompetitorAlreadyExistsRuntimeException;
import br.edu.opi.manager.competitor.exceptions.CompetitorNotFoundRuntimeException;
import br.edu.opi.manager.competitor.exceptions.SchoolNotNullRuntimeException;
import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import br.edu.opi.manager.excel_io.models.CompetitorTableRow;
import br.edu.opi.manager.person.models.Genre;
import br.edu.opi.manager.person.models.PartsPersonName;
import br.edu.opi.manager.person.models.Person;
import br.edu.opi.manager.person.services.PersonService;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.school.models.Grade;
import br.edu.opi.manager.school.models.School;
import br.edu.opi.manager.school.repositories.SchoolRepository;
import br.edu.opi.manager.student.models.Student;
import br.edu.opi.manager.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
public class CompetitorService extends GenericService<Long, Competitor, CompetitorRepository> {

	private StudentRepository studentRepository;

	private PersonService personService;

	private SchoolRepository schoolRepository;

	@Autowired
	public CompetitorService(
			StudentRepository studentRepository,
			PersonService personService,
			SchoolRepository schoolRepository) {
		this.studentRepository = studentRepository;
		this.personService = personService;
		this.schoolRepository = schoolRepository;
	}

	public Page<Competitor> index(Integer page, Integer size, String delegatePrincipal) {
		School school = schoolRepository.findByDelegateUsername(delegatePrincipal);
		if (school == null) {
			throw new SchoolNotNullRuntimeException(delegatePrincipal);
		}
		if (page == null) {
			page = DEFAULT_PAGE;
		}
		if (size == null) {
			size = DEFAULT_SIZE;
		}
		return repository.findAllByStudentSchoolId(school.getId(), PageRequest.of(page, size, DEFAULT_SORT));
	}

	public Competitor show(Long id, String delegatePrincipal) {
		School school = schoolRepository.findByDelegateUsername(delegatePrincipal);
		if (school == null) {
			throw new SchoolNotNullRuntimeException(delegatePrincipal);
		}
		Competitor savedCompetitor = repository.findByIdAndStudentSchoolId(id, school.getId());
		if (savedCompetitor == null) {
			throw new CompetitorNotFoundRuntimeException();
		}
		return savedCompetitor;
	}

	public Competitor create(Competitor competitor, String delegatePrincipal) {
		List<Competitor> competitors = repository.findAllByStudentSchoolDelegateUsernameAndStudentIdAndYear(delegatePrincipal, competitor.getStudent().getId(), competitor.getYear());
		if (!competitors.isEmpty()) {
			throw new CompetitorAlreadyExistsRuntimeException();
		}
		return this.create(competitor);
	}

	public Competitor update(Long id, Competitor competitor, String delegatePrincipal) {
		show(id, delegatePrincipal);
		return this.update(id, competitor);
	}

	public void delete(Long id, String delegatePrincipal) {
		show(id, delegatePrincipal);
		this.delete(id);
	}

	public void solveAndSave(Long schoolId, CompetitorTableRow competitorTableRow) {
		PartsPersonName parts = personService.processName(competitorTableRow.getName());
		List<Student> listSavedStudent = studentRepository
				.findByPersonAcronymAndPersonFirstNameAndPersonLastNameAndPersonDateBirth(
						parts.getAcronym(),
						parts.getFirstName(),
						parts.getLastName(),
						competitorTableRow.getDateBirth());
		Grade grade = competitorTableRow.getGrade();
		Double score = competitorTableRow.getScore();
		Student savedStudent = !listSavedStudent.isEmpty() ? listSavedStudent.get(0) : null; // TODO: if size > 0?
		if (savedStudent == null) {
			String name = competitorTableRow.getName();
			LocalDate dateBirth = competitorTableRow.getDateBirth();
			Genre genre = competitorTableRow.getGenre();
			Student student = new Student(new Person(name, dateBirth, genre), new School(schoolId));
			savedStudent = studentRepository.save(student);
			Competitor competitor = new Competitor(savedStudent, grade, score);
			create(competitor);
		} else {
			List<Competitor> listCompetitors = repository.findAllByStudentIdAndYear(savedStudent.getId(), Year.now().getValue());
			Competitor competitor = new Competitor(savedStudent, grade, score);
			if (!listCompetitors.isEmpty()) {
				Competitor savedCompetitor = listCompetitors.get(0); // TODO: if size > 0?
				update(savedCompetitor.getId(), competitor);
			} else {
				create(competitor);
			}
		}
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
