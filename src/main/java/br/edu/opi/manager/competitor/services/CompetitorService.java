package br.edu.opi.manager.competitor.services;

import br.edu.opi.manager.competitor.exceptions.CompetitorAlreadyExistsRuntimeException;
import br.edu.opi.manager.competitor.exceptions.CompetitorNotFoundRuntimeException;
import br.edu.opi.manager.competitor.exceptions.SchoolNotNullRuntimeException;
import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import br.edu.opi.manager.office_io.models.CompetitorTableRow;
import br.edu.opi.manager.olympiad.models.OpiAward;
import br.edu.opi.manager.olympiad.models.OpiCategory;
import br.edu.opi.manager.olympiad.models.OpiLevels;
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

	private static final Integer RANKING_PAGE = 0;
	private static final Integer RANKING_SIZE = 60;

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

	public Page<Competitor> index(Integer page, Integer size, String level, String category) {
		if (page == null) {
			page = DEFAULT_PAGE;
		}
		if (size == null) {
			size = DEFAULT_SIZE;
		}
		Page<Competitor> solve = solveFindLevelAndCategory(page, size, level, category);
		if (solve != null) {
			return solve;
		}
		return repository.findAll(PageRequest.of(page, size, DEFAULT_SORT));
	}

	public Page<Competitor> index(Integer page, Integer size, String level, String category, String delegatePrincipal) {
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
		Page<Competitor> solve = solveFindLevelAndCategory(page, size, level, category);
		if (solve != null) {
			return solve;
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

	public void solveAndSave(School school, CompetitorTableRow competitorTableRow) {
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
			Student student = new Student(new Person(name, dateBirth, genre), school);
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

	public void solveAndUpdate(CompetitorTableRow competitor) {
		Long competitorId = competitor.getHash().longValue();
		Double score = competitor.getScore();
		Competitor savedCompetitor = repository.findById(competitorId).orElseThrow(CompetitorNotFoundRuntimeException::new); // TODO: error
		savedCompetitor.setScoreLevelTwo(score);
		repository.save(savedCompetitor);
	}

	public static Double calculateFinalScore(Competitor competitor) {
		Double scoreLevelOne = competitor.getScoreLevelOne();
		Double scoreLevelTwo = competitor.getScoreLevelTwo();
		if (validateScore(scoreLevelOne) || validateScore(scoreLevelTwo)) {
			return 0.0;
		}
		return scoreLevelOne + 20.0 * scoreLevelTwo; // @see repository.findAllByCategoryAndYear
	}

	public Page<Competitor> ranking(String category, Integer year, Integer page, Integer size) {
		if (page == null) {
			page = RANKING_PAGE;
		}
		if (size == null) {
			size = RANKING_SIZE;
		}
		OpiCategory opiCategory = OpiCategory.from(category);
		return repository.findAllByCategoryAndYear(opiCategory, year, PageRequest.of(page, size));
	}

	public void rewarding(Long competitorId, String award) {
		OpiAward opiAward = OpiAward.from(award);
		Competitor savedCompetidor = this.show(competitorId);
		savedCompetidor.setAward(opiAward);
		repository.save(savedCompetidor);
	}

	private static boolean validateScore(Double score) {
		return score == null || score <= 0.0;
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

	private Page<Competitor> solveFindLevelAndCategory(Integer page, Integer size, String level, String category) {
		if (level != null && category != null) {
			return repository.findAllByLevelAndCategory(OpiLevels.from(level), OpiCategory.from(category), PageRequest.of(page, size, DEFAULT_SORT));
		}
		if (level != null) {
			return repository.findAllByLevel(OpiLevels.from(level), PageRequest.of(page, size, DEFAULT_SORT));
		}
		if (category != null) {
			return repository.findAllByCategory(OpiCategory.from(category), PageRequest.of(page, size, DEFAULT_SORT));
		}
		return null;
	}

}
