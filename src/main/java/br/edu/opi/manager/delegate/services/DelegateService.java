package br.edu.opi.manager.delegate.services;

import br.edu.opi.manager.delegate.exceptions.DelegateNotFoundRuntimeException;
import br.edu.opi.manager.delegate.exceptions.DelegateNotNullRuntimeException;
import br.edu.opi.manager.delegate.models.Delegate;
import br.edu.opi.manager.delegate.repositories.DelegateRepository;
import br.edu.opi.manager.project_patterns.models.user.ProfileFactory;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.security.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class DelegateService extends GenericService<Long, Delegate, DelegateRepository> {

	public Delegate show(String delegatePrincipal) {
		Delegate delegate = repository.findByUsername(delegatePrincipal);
		if (delegate == null) {
			throw new DelegateNotFoundRuntimeException(delegatePrincipal);
		}
		return delegate;
	}

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
