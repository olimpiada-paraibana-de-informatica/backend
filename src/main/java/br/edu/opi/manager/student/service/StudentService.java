package br.edu.opi.manager.student.service;

import br.edu.opi.manager.project_patterns.exceptions.NotFoundRuntimeException;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.school.model.School;
import br.edu.opi.manager.school.repository.SchoolRepository;
import br.edu.opi.manager.student.exception.SchoolNotNullRuntimeException;
import br.edu.opi.manager.student.model.Student;
import br.edu.opi.manager.student.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends GenericService<Long, Student, StudentRepository> {

	private SchoolRepository schoolRepository;

	public StudentService(SchoolRepository schoolRepository) {
		this.schoolRepository = schoolRepository;
	}

	public Page<Student> index(Integer page, Integer size, String delegatePrincipal) {
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
		return repository.findAllBySchoolId(school.getId(), PageRequest.of(page, size, DEFAULT_SORT));
	}

	public Student show(Long id, String delegatePrincipal) {
		School school = schoolRepository.findByDelegateUsername(delegatePrincipal);
		if (school == null) {
			throw new SchoolNotNullRuntimeException(delegatePrincipal);
		}
		Student savedStudent = repository.findByIdAndSchoolId(id, school.getId());
		if (savedStudent == null) {
			throw new NotFoundRuntimeException();
		}
		return savedStudent;
	}

	public Student create(Student student, String delegatePrincipal) {
		School school = schoolRepository.findByDelegateUsername(delegatePrincipal);
		if (school == null) {
			throw new SchoolNotNullRuntimeException(student.getName(), student.getDateBirth());
		}
		student.setSchool(new School(school.getId()));
		return this.create(student);
	}

	public Student update(Long id, Student student, String delegatePrincipal) {
//		School school = schoolRepository.findByDelegateUsername(delegatePrincipal);
//		if (school == null) {
//			throw new SchoolNotNullRuntimeException(delegatePrincipal);
//		}
//		Student savedStudent = repository.findByIdAndSchoolId(id, school.getId());
//		if (savedStudent == null) {
//			throw new NotFoundRuntimeException();
//		}
		show(id, delegatePrincipal);
		return this.update(id, student);
	}

	public void delete(Long id, String delegatePrincipal) {
		show(id, delegatePrincipal);
		this.delete(id);
	}

	@Override
	public void validateBeforeCreate(Student student) {
	}

	@Override
	public void validateBeforeUpdate(Student student) {
	}

	@Override
	public void validateBeforeDelete(Long id) {
	}

}
