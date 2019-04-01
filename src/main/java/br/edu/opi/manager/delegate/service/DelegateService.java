package br.edu.opi.manager.delegate.service;

import br.edu.opi.manager.delegate.exception.DelegateNotNullRuntimeException;
import br.edu.opi.manager.project_patterns.models.user.ProfileFactory;
import br.edu.opi.manager.security.SecurityUtils;
import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.delegate.repository.DelegateRepository;
import br.edu.opi.manager.project_patterns.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class DelegateService extends GenericService<Long, Delegate, DelegateRepository> {

	@Override
	public void validateBeforeCreate(Delegate delegate) {
		if (delegate == null || delegate.getUsername() == null) {
			throw new DelegateNotNullRuntimeException();
		}
		if (delegate.getPassword() == null) {
			String password = SecurityUtils.generateFriendlyPassword();
			delegate.setPassword(password);
		}
		if (delegate.getProfile() == null) {
			delegate.setProfile(ProfileFactory.delegateUser());
		}
	}

	@Override
	public void validateBeforeUpdate(Delegate model) {
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}

}
