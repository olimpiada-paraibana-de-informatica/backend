package br.edu.opi.manager.school.service;

import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.delegate.repository.DelegateRepository;
import br.edu.opi.manager.delegate.service.DelegateService;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.school.exception.DelegateNotNullRuntimeException;
import br.edu.opi.manager.school.exception.SchoolExistsRuntimeException;
import br.edu.opi.manager.school.model.School;
import br.edu.opi.manager.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService extends GenericService<Long, School, SchoolRepository> {

	private DelegateService delegateService;

	private DelegateRepository delegateRepository;

	@Autowired
	public SchoolService(
			DelegateRepository delegateRepository,
			DelegateService delegateService) {
		this.delegateRepository = delegateRepository;
		this.delegateService = delegateService;
	}

	@Override
	public void validateBeforeCreate(School school) {
		solveSchool(school);
		solveDelegate(school);
	}

	@Override
	public void validateBeforeUpdate(School school) {
		solveDelegate(school);
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}

	private void solveSchool(School school) {
		Delegate delegate = school.getDelegate();
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
