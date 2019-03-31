package br.edu.opi.manager.delegate.service;

import br.edu.opi.manager.conventions.services.GenericService;
import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.delegate.repository.DelegateRepository;
import org.springframework.stereotype.Service;

@Service
public class DelegateService extends GenericService<Long, Delegate, DelegateRepository> {

}