package br.edu.opi.manager.school.services;

import br.edu.opi.manager.delegate.models.Delegate;
import br.edu.opi.manager.delegate.repositories.DelegateRepository;
import br.edu.opi.manager.delegate.services.DelegateService;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.school.exceptions.DelegateNotNullRuntimeException;
import br.edu.opi.manager.school.exceptions.SchoolExistsRuntimeException;
import br.edu.opi.manager.school.exceptions.UserNotDelegateRuntimeException;
import br.edu.opi.manager.school.models.School;
import br.edu.opi.manager.school.repositories.SchoolRepository;
import br.edu.opi.manager.student.exceptions.SchoolNotNullRuntimeException;
import br.edu.opi.manager.user.models.UserModel;
import br.edu.opi.manager.user.repositories.UserRepository;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SchoolService extends GenericService<Long, School, SchoolRepository> {

	private DelegateService delegateService;

	private DelegateRepository delegateRepository;

	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public SchoolService(
			DelegateRepository delegateRepository,
			DelegateService delegateService,
			UserRepository userRepository) {
		this.delegateRepository = delegateRepository;
		this.delegateService = delegateService;
		this.userRepository = userRepository;
	}

	public School show(String delegatePrincipal) {
		School school = repository.findByDelegateUsername(delegatePrincipal);
		if (school == null) {
			throw new SchoolNotNullRuntimeException(delegatePrincipal);
		}
		return school;
	}

	public School update(Long id, School school, String delegatePrincipal) {
		School savedSchool = repository.findByDelegateUsername(delegatePrincipal);
		if (savedSchool == null) {
			throw new SchoolNotNullRuntimeException(delegatePrincipal);
		}
		return this.update(savedSchool.getId(), school);
	}

	@Override
	public void validateBeforeCreate(School school) {
		solveSchool(school);
		solveCommonUser(school);
		solveDelegate(school);
	}

	@Override
	public void validateBeforeUpdate(School school) {
		solveDelegate(school);
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}

	private void solveCommonUser(School school) {
		Delegate delegate = school.getDelegate();
		if (delegate == null || delegate.getUsername() == null) {
			throw new DelegateNotNullRuntimeException();
		}
		UserModel user = userRepository.findByUsername(delegate.getUsername());
		if (user != null) {
			throw new UserNotDelegateRuntimeException();
		}
	}

	private void solveSchool(School school) {
		Delegate delegate = school.getDelegate();
		if (delegate == null || delegate.getUsername() == null) {
			throw new DelegateNotNullRuntimeException();
		}
		School savedSchool = repository.findByDelegateUsername(delegate.getUsername());
		if (savedSchool != null) {
			throw new SchoolExistsRuntimeException(savedSchool.getName());
		}
	}

	private void solveDelegate(School school) {
		Delegate delegate = school.getDelegate();
		if (delegate == null || delegate.getUsername() == null) {
			throw new DelegateNotNullRuntimeException();
		}
		Delegate savedDelegate = delegateRepository.findByUsername(delegate.getUsername());
		if (savedDelegate != null) {
			delegate = delegateService.update(savedDelegate.getId(), delegate);
		} else {
			delegate = delegateService.create(delegate);
		}
		school.setDelegate(new Delegate(delegate.getId()));
	}

	public void resetFilled() {
		entityManager.createNativeQuery("UPDATE tb_school SET filled = false").setHint(QueryHints.SPEC_HINT_TIMEOUT, 2000);
	}

}
