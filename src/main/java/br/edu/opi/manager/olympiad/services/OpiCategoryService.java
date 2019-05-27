package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.olympiad.models.OpiCategory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OpiCategoryService {

	public List<OpiCategory> index() {
		List<OpiCategory> categories = new LinkedList<>();
		for (OpiCategory category : OpiCategory.values()) {
			if (!category.equals(OpiCategory.INICIACAO_1_PUB) && !category.equals(OpiCategory.INICIACAO_2_PUB)) {
				categories.add(category);
			}
		}
		return categories;
	}

}
