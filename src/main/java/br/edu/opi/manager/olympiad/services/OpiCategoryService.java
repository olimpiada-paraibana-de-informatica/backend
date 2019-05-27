package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.olympiad.models.OpiCategory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OpiCategoryService {

	public List<OpiCategory> index() {
		List<OpiCategory> categories = Arrays.asList(OpiCategory.values());
		categories.remove(OpiCategory.INICIACAO_1_PUB);
		categories.remove(OpiCategory.INICIACAO_2_PUB);
		return categories;
	}

}
