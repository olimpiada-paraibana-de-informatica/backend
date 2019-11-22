package br.edu.opi.manager.html.services;

import br.edu.opi.manager.html.models.Html;
import br.edu.opi.manager.html.repositories.HtmlRepository;
import br.edu.opi.manager.project_patterns.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class HtmlService extends GenericService<String, Html, HtmlRepository> {

	@Override
	public void validateBeforeCreate(Html model) {
	}

	@Override
	public void validateBeforeUpdate(Html model) {
	}

	@Override
	public void validateBeforeDelete(String s) {
	}

}
