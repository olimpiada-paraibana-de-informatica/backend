package br.edu.opi.manager.school.service;

import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.delegate.repository.DelegateRepository;
import br.edu.opi.manager.delegate.service.DelegateService;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.school.exception.DelegateNotNullRuntimeException;
import br.edu.opi.manager.school.exception.SchoolExistsRuntimeException;
import br.edu.opi.manager.school.exception.UserNotDelegateRuntimeException;
import br.edu.opi.manager.school.model.School;
import br.edu.opi.manager.school.repository.SchoolRepository;
import br.edu.opi.manager.user.model.UserModel;
import br.edu.opi.manager.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService extends GenericService<Long, School, SchoolRepository> {

	private DelegateService delegateService;

	private DelegateRepository delegateRepository;

	private UserRepository userRepository;

	@Autowired
	public SchoolService(
			DelegateRepository delegateRepository,
			DelegateService delegateService,
			UserRepository userRepository) {
		this.delegateRepository = delegateRepository;
		this.delegateService = delegateService;
		this.userRepository = userRepository;
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

}
