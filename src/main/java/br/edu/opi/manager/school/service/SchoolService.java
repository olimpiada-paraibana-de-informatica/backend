package br.edu.opi.manager.school.service;

import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.delegate.service.DelegateService;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.school.exception.DelegateNotNullRuntimeException;
import br.edu.opi.manager.school.model.School;
import br.edu.opi.manager.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService extends GenericService<Long, School, SchoolRepository> {

	private DelegateService delegateService;

	@Autowired
	public SchoolService(DelegateService delegateService) {
		this.delegateService = delegateService;
	}

	@Override
	public void validateBeforeCreate(School school) {
		Delegate delegate = school.getDelegate();
		if (delegate == null || delegate.getUsername() == null) {
			throw new DelegateNotNullRuntimeException();
		}
		if (delegate.getId() == null) {
			delegate = delegateService.create(delegate);
		}
		school.setDelegate(new Delegate(delegate.getId()));
	}

	@Override
	public void validateBeforeUpdate(School model) {
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}

}
