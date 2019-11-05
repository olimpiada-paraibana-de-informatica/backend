package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.olympiad.dtos.CompetitorChampionOutput;
import br.edu.opi.manager.olympiad.dtos.ResultCategoryOutput;
import br.edu.opi.manager.olympiad.dtos.ResultsCategoryAndYearsOutput;
import br.edu.opi.manager.olympiad.models.OpiCategory;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class OpiResultsService {

	private static final int TOTAL_CATEGORIAS = 7;

	public List<ResultsCategoryAndYearsOutput> resultsYears() {
		List<ResultsCategoryAndYearsOutput> results = new LinkedList<>();
		String type = "";
		int ini, FIM = 2019;
		for (int i = 0; i < TOTAL_CATEGORIAS; i++) {
			List<Integer> years = new ArrayList<>();
			switch (i) {
				case 0:
					type = "iniciacao1";
					ini = OpiCategory.INICIACAO_1.getBeginYear();
					break;
				case 1:
					type = "iniciacao1Pub";
					ini = OpiCategory.INICIACAO_1_PUB.getBeginYear();
					break;
				case 2:
					type = "iniciacao2";
					ini = OpiCategory.INICIACAO_2.getBeginYear();
					break;
				case 3:
					type = "iniciacao2Pub";
					ini = OpiCategory.INICIACAO_2_PUB.getBeginYear();
					break;
				case 4:
					type = "programacao";
					ini = OpiCategory.PROGRAMACAO.getBeginYear();
					break;
				case 5:
					type = "avancadoJunior";
					ini = OpiCategory.AVANCADO_JUNIOR.getBeginYear();
					break;
				case 6:
					type = "avancadoSenior";
					ini = OpiCategory.AVANCADO_SENIOR.getBeginYear();
					break;
				default:
					throw new IllegalStateException("Não esperava " + i + " categorias");
			}
			// TODO: guardar FIM de resultados num BD, daí só precisa recuperar o valor fim
			for (int year = ini; year <= FIM; year++) {
				years.add(year);
			}
			results.add(new ResultsCategoryAndYearsOutput(type, years));
		}
		return results;
	}

	public List<ResultCategoryOutput> resultCategory(OpiCategory category) {
		List<ResultCategoryOutput> results = new LinkedList<>();
		List<CompetitorChampionOutput> gold;
		List<CompetitorChampionOutput> silver;
		List<CompetitorChampionOutput> bronze;
		List<CompetitorChampionOutput> honorableMention;
		switch (category) {
			case INICIACAO_1:
				gold = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				silver = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				bronze = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				honorableMention = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				results.add(new ResultCategoryOutput(gold, silver, bronze, honorableMention));
				break;
			case INICIACAO_1_PUB:
				gold = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				silver = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				bronze = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				honorableMention = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				results.add(new ResultCategoryOutput(gold, silver, bronze, honorableMention));
				break;
			case INICIACAO_2:
				gold = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				silver = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				bronze = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				honorableMention = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				results.add(new ResultCategoryOutput(gold, silver, bronze, honorableMention));
				break;
			case INICIACAO_2_PUB:
				gold = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				silver = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				bronze = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				honorableMention = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				results.add(new ResultCategoryOutput(gold, silver, bronze, honorableMention));
				break;
			case PROGRAMACAO:
				gold = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				silver = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				bronze = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				honorableMention = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				results.add(new ResultCategoryOutput(gold, silver, bronze, honorableMention));
				break;
			case AVANCADO_JUNIOR:
				gold = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				silver = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				bronze = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				honorableMention = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				results.add(new ResultCategoryOutput(gold, silver, bronze, honorableMention));
				break;
			case AVANCADO_SENIOR:
				gold = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				silver = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				bronze = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				honorableMention = new ArrayList<CompetitorChampionOutput>() {{
					add(new CompetitorChampionOutput(1, "Herbrand Machado Gomes", "Motiva", "Campina Grande"));
					add(new CompetitorChampionOutput(1, "Davi Barros de Sena", "Motiva", "João Pessoa"));
					add(new CompetitorChampionOutput(1, "Anellyse Vivianne de Souza Ramos", "IPEN", "Campina Grande"));
				}};
				results.add(new ResultCategoryOutput(gold, silver, bronze, honorableMention));
				break;
		}
		return results;
	}

}
