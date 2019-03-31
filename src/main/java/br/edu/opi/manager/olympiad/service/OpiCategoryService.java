package br.edu.opi.manager.olympiad.service;

import br.edu.opi.manager.olympiad.model.OpiCategory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OpiCategoryService {

	public List<OpiCategory> index() {
		return Arrays.asList(OpiCategory.values());
	}

}
