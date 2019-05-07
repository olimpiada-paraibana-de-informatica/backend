package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.olympiad.models.OpiCategory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OpiCategoryService {

	public List<OpiCategory> index() {
		return Arrays.asList(OpiCategory.values());
	}

}
