package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.olympiad.dtos.CompetitorChampionOutput;
import br.edu.opi.manager.olympiad.dtos.ResultCategoryOutput;
import br.edu.opi.manager.olympiad.dtos.ResultsCategoryAndYearsOutput;
import br.edu.opi.manager.olympiad.models.OpiCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class OpiResultsService {

	/*
	 * TODO: migrar dados das escolas e competidores de todos os documentos
	 * gerados para a OPI até então. Anexar os dados 'mockados' abaixo nessa
	 * relação.
	 */

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
			for (int year = FIM; year >= ini; year--) {
				years.add(year);
			}
			results.add(new ResultsCategoryAndYearsOutput(type, years));
		}
		return results;
	}

	public ResultCategoryOutput resultCategory(OpiCategory category, Integer year) {
		ResultCategoryOutput result = new ResultCategoryOutput();
		List<CompetitorChampionOutput> gold;
		List<CompetitorChampionOutput> silver;
		List<CompetitorChampionOutput> bronze;
		List<CompetitorChampionOutput> honorableMention;
		if (OpiCategory.INICIACAO_1.equals(category) && year == 2019) {
			gold = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Herbrand Machado Gomes"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Davi Barros de Sena"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Anellyse Vivianne de Souza Ramos"));
			}};
			silver = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Eluize Gianna Lucena De Medeiros"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Henrique Donizzette Vasconcelos Gonzaga De Faria"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Kaua Felipe Fernandes De Azevedo"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Rafael Ramos De Queiroz Ribeiro"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Luiz Otavio Raposo Araujo"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Artur Lima Do Rego"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Rai de Souza Coelho Soares"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Rodrigo Silva Costa"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Nathália Barbosa De Azevedo "));
			}};
			bronze = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Heitor José Morais Lira Soares"));
				add(new CompetitorChampionOutput(1, "DAMAS", "Campina Grande", "Júlia Loes Barbosa Barros"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Pedro Sarmento Campelo"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Larissa Simões Peixoto"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Diego Silveira Rodrigues"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Antonio Da Costa França"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Ayla Vieira Moita"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Cecilia Ramos Gomes"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Larissa Terras Veras"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Vinicius Barbosa De Lima"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Maria Vitória Cavalcanti Figueiredo"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Leticia Rocha De Almeida"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Ana Catarina Souto De Oliveira"));
				add(new CompetitorChampionOutput(1, "DAMAS", "Campina Grande", "Jorge Miguel Da Costa Pinto"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Ana Luísa Barbosa De Mélo Araujo"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Larissa Mesquita Guerra Cavalcante De Oliveira"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "José Rodrigues Da Silva Neto"));
			}};
			honorableMention = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Letícia Ribeiro Ventura Costa"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Livia Pessoa Lopes Da Silva"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Nicole Crispim Vasconcelos"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Alexandre Andrade De Farias"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Lucas Menezes Melo"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Ana Louyse Maciel Batista"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Alice Luna Pinto Dias"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Lucas Gomes Pires"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Maria Luisa Medeiros Nepomuceno Araujo"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Marcos Vinícius Antunes De Araújo"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Joao Rafael Ponciano Da Costa"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Davi Santos Lisboa"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Júlia Cruz De Veras Souza"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Lorena De Britto Lyra Nóbrega"));
			}};
		} else if (OpiCategory.INICIACAO_1_PUB.equals(category) && year == 2019) {
			gold = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Afonso Alves dos Santos"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "José Anderson Viana Duarte"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Lucas Emílio dos Santos Belarmino"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Maria Renata do Nascimento Santos"));
				add(new CompetitorChampionOutput(1, "José Rodrigues", "Areia", "Natali de Brito Silva"));
			}};
			silver = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Jônatas dos Santos Belarmino"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Roberta de Souza Santos"));
			}};
			bronze = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "José Rodrigues", "Areia", "Emilly Vitoria Galdino de Brito"));
				add(new CompetitorChampionOutput(1, "José Rodrigues", "Areia", "Hellen Beatriz Caetano de Souza"));
				add(new CompetitorChampionOutput(1, "Madre Trautlinde", "Areia", "Kemilly Joyce dos Santos"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Samuel Alves do Nascimento"));
				add(new CompetitorChampionOutput(1, "Madre Trautlinde", "Areia", "Sara de Lima Cardoso"));
			}};
			honorableMention = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "José Rodrigues", "Areia", "Ana Maria Policarpo Santos"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Anna Nery Pereira Galdino"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Daniel da Silva Souza"));
				add(new CompetitorChampionOutput(1, "José Rodrigues", "Areia", "Jeferson Vinicius Dias Galdino"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "José Eduardo Pereira da Silva"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "José Miguel dos Santos"));
				add(new CompetitorChampionOutput(1, "Lázaro Emanuel dos S. Maribondo da Silva", "Abel Barbosa", "Areia"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Sarah Thalita Pereira de Araújo"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Amanda Silva de Souza"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Esmeralda Gonçalves de Oliveira"));
				add(new CompetitorChampionOutput(1, "José Rodrigues", "Areia", "Grasielly Moreno dos Santos"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Kethyllen Kleanny Machado dos Santos"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Letícia Trajano dos Santos"));
				add(new CompetitorChampionOutput(1, "Madre Trautlinde", "Areia", "Marina Sabino Barboza Silva"));
				add(new CompetitorChampionOutput(1, "Abel Barbosa", "Areia", "Pedro Antônio Gonçalves da Silva"));
			}};
		} else if (OpiCategory.INICIACAO_2.equals(category) && year == 2019) {
			gold = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Mateus Sales Ramalho"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Jose Francisco Cruz Neto"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Samuel Victor Fernandes Dantas Vicente"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Sofia Jordão Figueredo De Melo"));
			}};
			silver = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Romulo Guimarães Nogueira Júnior"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Letícia Pedrosa Borges Gheyi"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Beatriz De Oliveira Fernandes"));
				add(new CompetitorChampionOutput(1, "ISO", "João Pessoa", "Artur Tin Lun Dai Lima"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Pedro Pontes De Azevêdo"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Matheus Moreira Brito Medeiros"));
			}};
			bronze = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Lucas Araújo Medeiros"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Mateus Farias De Oliveira"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Livia Caroline Dos Santos Carvalho"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Rebeca Josita De Almeida Bezerra"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Matteus Marinho Cantisani"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Elaine Regina Macedo Campelo Dias"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Letícia Luna Dias Barbosa"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Gabriel Mangueira Cunha"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Rebeca Maria De Araujo Oliveira"));
				add(new CompetitorChampionOutput(1, "ISO", "João Pessoa", "Filipe Ya Hu Dai Lima"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "José Vitor Andrade Nogueira"));
				add(new CompetitorChampionOutput(1, "Damas", "Campina Grande", "Evelyn Melo Ramos"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Héllida Louize Lima Duarte Gomes"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Samuel Chaves Morais De Lima"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Felipe Rodrigues Coelho"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Pedro Henrique De Moura Bezerra"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Davi Riquelme Albuquerque Dos Santos"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Isadora Pereira De Castro E Figueiredo"));
				add(new CompetitorChampionOutput(1, "Damas", "Campina Grande", "Ana Beatriz Monteiro Santos"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Gabriel Gouveia Dias Bittencourt"));
				add(new CompetitorChampionOutput(1, "Damas", "Campina Grande", "Giovana Florencio Leite"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Maria Eduarda Montenegro Tenório"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Bernardo Siqueira Batista"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Letícia Lopes Borba"));
				add(new CompetitorChampionOutput(1, "Damas", "Campina Grande", "Ana Luisa Lopes Carneiro De Aragão Monteiro"));
				add(new CompetitorChampionOutput(1, "Damas", "Campina Grande", "Hádria Lais Da Costa Santos Lira"));
			}};
			honorableMention = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Damas", "Campina Grande", "Julia Oliveira Morais Pinheiro"));
				add(new CompetitorChampionOutput(1, "Damas", "Campina Grande", "Marta Maria Pessoa Batista"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Ana Beatriz Mamede De Sousa"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Milla Luna Sibeon"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Hilbert Machado Gomes"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Maria Alice De Vasconcelos Wanderley Félix"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Victor Oliveira Galdino De Araújo"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Eduardo Resende Veras Alcantara"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Hilda Glicia Fonseca Fortes"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Saulo Contreras De Assis"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Arthur Feitosa De Oliveira"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Joao Pedro Duarte Luna"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Mariana Lucena Lins"));
			}};
		} else if (OpiCategory.INICIACAO_2_PUB.equals(category) && year == 2019) {
			gold = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Sabrina Nayara Souza da Silva"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Fabio Vinicius de Souza Santos"));
			}};
			silver = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Tailan Barboza da Silva"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Ana Beatriz Alves dos Santos"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Leonel Lafaiette Santos Silva"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Beatriz Souza Rodrigues"));
			}};
			bronze = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Adailton Freire da Silva"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Ana Claudia de Lima Fidelis"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Gabriel Luan Santos da Silva"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Marcondes Alves de Souza"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Mayane Serafim da Silva"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Robson de Souza Silva"));
				add(new CompetitorChampionOutput(1, "Carlota Barreira", "Areia", "João Batista Santos Silva"));
				add(new CompetitorChampionOutput(1, "José Américo de Almeida", "Areia", "Gisele Pereira de Sousa Silva"));
				add(new CompetitorChampionOutput(1, "Madre Trautlinde", "Areia", "Yasmim Pereira da Silva Freitas"));
			}};
			honorableMention = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Adailton Alfredo dos Santos Filho"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Alan dos Santos Goncalves"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Anderson de Oliveira Silva"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Andressa Barbosa Sousa"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Esmeralda Laurentino dos Santos"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Evellyn Nayara dos Santos Vital"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Gabriella Martins Sancao"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Joalison dos Santos Targino"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Kimillyn Carla da Silva Oliveira"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Magna dos Santos Gondim"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Maria Fernanda da Silva Minervino"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Samara Jenifer da Silva de Sousa"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Vitor Emanuel dos Santos Alves"));
				add(new CompetitorChampionOutput(1, "Álvaro Machado", "Areia", "Yasmim Aparecida dos Santos Silva"));
				add(new CompetitorChampionOutput(1, "José Américo de Almeida", "Areia", "Alessandro de Souza Silva"));
				add(new CompetitorChampionOutput(1, "José Américo de Almeida", "Areia", "Bruno Weslen da Silva Andrade"));
				add(new CompetitorChampionOutput(1, "José Américo de Almeida", "Areia", "Gustavo Pereira da Silva"));
				add(new CompetitorChampionOutput(1, "José Américo de Almeida", "Areia", "Thalyta Trajando Gouveia"));
				add(new CompetitorChampionOutput(1, "Madre Trautlinde", "Areia", "Ana Paula Cruz dos Santos"));
				add(new CompetitorChampionOutput(1, "Madre Trautlinde", "Areia", "Irlanda Maira Rodrigues"));
			}};
		} else if (OpiCategory.PROGRAMACAO.equals(category) && year == 2019) {
			gold = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "IFPB", "Guarabira", "Douglas Alves de Sousa"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Daví Henrique Silva Guimarães"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Augusto Nunes Zacarias"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Victor de Paula Fonseca"));
			}};
			silver = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "IFPE", "Garanhuns", "Kyllder Almeida Da Rocha"));
				add(new CompetitorChampionOutput(1, "IFPB", "Campina Grande", "João Vitor Silva Luciano"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Julia Motta Coelho De Cerqueira Paes"));
				add(new CompetitorChampionOutput(1, "IFPB", "João Pessoa", "João Rafael De Souza Nogueira Rodrigues"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Abraão Caiana De Freitas"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "José Ítalo Barbosa De Brito"));
				add(new CompetitorChampionOutput(1, "IFPB", "Esperança", "Emanuel Evandro Cyrino Eleutério"));
				add(new CompetitorChampionOutput(1, "IFPB", "João Pessoa", "Petrus Pierre Ormesino Bento"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Amy Luna Sibeon"));
				add(new CompetitorChampionOutput(1, "IFPB", "Esperança", "Cristovão Pessoa Cândido Neto"));
			}};
			bronze = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "IFPB", "Guarabira", "Samira Kaline Silva Melo"));
				add(new CompetitorChampionOutput(1, "IFPB", "Campina Grande", "Jônatas Tavares Dos Santos"));
				add(new CompetitorChampionOutput(1, "IFPB", "Campina Grande", "Catarina Ramalho Dos Santos"));
				add(new CompetitorChampionOutput(1, "IFPB", "Campina Grande", "Rembrandt Asafe Vasconcelos Costa"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Maria Luisa Moraes Monteiro Dantas"));
				add(new CompetitorChampionOutput(1, "IFPB", "Campina Grande", "Irlla Martins Barbosa Da Silva"));
				add(new CompetitorChampionOutput(1, "IFPE", "Garanhuns", "Guilherme Pontes Melquiades"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Pedro Henriques Pereira"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Weslley Matheus Amador Nunes Pereira Batista"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Filipo Nathan Galvão Macedo"));
				add(new CompetitorChampionOutput(1, "ISO", "João Pessoa", "Filipe Ya Hu Dai Lima"));
				add(new CompetitorChampionOutput(1, "IFPB", "João Pessoa", "Leonardo Matheus Freitas Coutinho"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Tiago Alexandre Costa Santos"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Pedro Arthur De Oliveira Barreto"));
				add(new CompetitorChampionOutput(1, "IFPB", "Campina Grande", "Welbber Vital Porto"));
				add(new CompetitorChampionOutput(1, "IFPB", "João Pessoa", "Nicoly Figueredo Pessoa De Almeida"));
				add(new CompetitorChampionOutput(1, "IFPB", "Esperança", "Carlos Eduardo De Araujo Silva"));
			}};
			honorableMention = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "IFPB", "João Pessoa", "Rafael Domingos Da Silva"));
				add(new CompetitorChampionOutput(1, "IFPB", "Guarabira", "Alysson Pereira Do Nascimento"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Fátima Maria Thaiz Da Fonte Gomes"));
				add(new CompetitorChampionOutput(1, "IFPE", "Garanhuns", "Pedro Caetano Araújo Alves"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "João Pedro Campos Porto"));
			}};
		} else if (OpiCategory.AVANCADO_JUNIOR.equals(category)) {
			gold = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "IFPB-GB", "IFPB-GB", "Samira Kaline Silva Melo", "Douglas Alves de Sousa", "Pedro Henrique Gomes dos Santos"));
				add(new CompetitorChampionOutput(1, "UFCG", "UFCG", "Raphael de Paula Fonseca", "Lucas B. Herculano e Albuquerque","Rennan Rocha de Freitas"));
				add(new CompetitorChampionOutput(1, "IFPB-JP", "IFPB-JP", "Ádisson Matheus Paiva Ribeiro Tejó", "João Rafael de S. Nogueira Rodrigues", "Petrus Pierre Ormesino Bento"));
			}};
			silver = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva-JP", "Motiva-JP", "Victor de Paula Fonseca", "Júlia Motta", "Fátima Maria Thaiz Da Fonte"));
				add(new CompetitorChampionOutput(1, "Motiva-CG/IFPB-CG", "Motiva-CG/IFPB-CG", "Daví Henrique Silva Guimarães", "Abraão Caiana de Freitas", "Rembrandt Asafe Vasconcelos Costa"));
				add(new CompetitorChampionOutput(1, "UFCG", "UFCG", "Lucas Leal", "Rodrigo Estrela", "Ruan Gomes"));
			}};
			bronze = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "IFPB-JP", "IFPB-JP", "Andressa da Silva Theotônio Alves", "Julia Davet de Oliveira", "Villeneve de Oliveira Soares"));
				add(new CompetitorChampionOutput(1, "IFPB-ES", "IFPB-ES", "Cristovão Pessoa Cândido Neto", "Ismael Raimundo da Silva Neto", "Emanuel Evandro Cyrino Eleuterio"));
				add(new CompetitorChampionOutput(1, "UFCG", "UFCG", "Lucian Julio Felix da Costa", "Eniedson Fabiano Pereira", "Bruno Andrade Fernandes"));
			}};
			honorableMention = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva-CG", "Motiva-CG", "Pedro Arthur de Oliveira Barreto", "José Ítalo Barbosa de Brito", "Lorenzo Carrera de Oliveira"));
				add(new CompetitorChampionOutput(1, "IFPB-CG", "IFPB-CG", "Irlla Martins Barbosa da Silva", "João Vitor Silva Luciano", "Welbber Vital Porto"));
				add(new CompetitorChampionOutput(1, "IFPB-GB", "IFPB-GB", "Matheus Cesar", "Luis Gustavo", "Eliseu Felipe"));
			}};
		} else if (OpiCategory.AVANCADO_SENIOR.equals(category)) {
			gold = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "UFCG", "UFCG", "José Amandio Ferreira dos Santos", "Daniel de Matos Figueredo", "João Marcos Lima Medeiros"));
				add(new CompetitorChampionOutput(1, "UFCG", "UFCG", "Gabriel Alves Tavares", "Matheus Oliveira Pereira", "Gabriel Felipe Cardoso Gomes"));
				add(new CompetitorChampionOutput(1, "UFCG/Motiva-CG", "UFCG/Motiva-CG", "Lucas Arcoverde", "Maria Luisa Dantas", "Anderson Kleber Dantas"));
			}};
			silver = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "UFCG", "UFCG", "Gustavo Bezerra Ribeiro", "Mariana M. dos Santos Dela-Bianca", "Vitória Heliane P. dos S. Sobrinha"));
				add(new CompetitorChampionOutput(1, "UFCG", "UFCG", "José Renan Silva Luciano", "Rodrigo Felipe Medeiros Pinto", "Júlio Barreto Guedes da Costa"));
				add(new CompetitorChampionOutput(1, "UEPB", "UEPB", "Adson de Macêdo Nascimento", "Thairam Michel Santos Ataíde", "Ramon Rodrigues de Sales"));
			}};
			bronze = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "IFPB-JP", "IFPB-JP", "Calebe Oliveira de Figueirêdo", "Mateus de Lima Melo", "Victor Herbert Ferreira de Sousa"));
				add(new CompetitorChampionOutput(1, "IFPB-JP", "IFPB-JP", "Kerven Maciel M. de Albuquerque", "Leandro Henrique Batista da Silva"));
				add(new CompetitorChampionOutput(1, "UFCG", "UFCG", "Wesley Henrique Araújo Monte", "Henrique Castro Arriel", "Gaspar Soares de Alencar"));
			}};
			honorableMention = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "IFPB-JP", "IFPB-JP", "Igor Alexandre Stefan", "Jorge Ricardo S. A. Alencar de Souza", "Rafael Cavalcante Chaves"));
				add(new CompetitorChampionOutput(1, "UFCG", "UFCG", "Flávio Farias", "Alexandre Ribeiro", "Eduardo Macedo"));
				add(new CompetitorChampionOutput(1, "UFCG", "UFCG", "Daniel Bezerra Galvão Mitre", "José Ignácio Morsch Schmid", "Paulo José Bastos Leitão"));
			}};
		} else {
			gold = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Herbrand Machado Gomes"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Davi Barros de Sena"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Anellyse Vivianne de Souza Ramos"));
			}};
			silver = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Eluize Gianna Lucena De Medeiros"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Henrique Donizzette Vasconcelos Gonzaga De Faria"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Kaua Felipe Fernandes De Azevedo"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Rafael Ramos De Queiroz Ribeiro"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Luiz Otavio Raposo Araujo"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Artur Lima Do Rego"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Rai de Souza Coelho Soares"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Rodrigo Silva Costa"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Nathália Barbosa De Azevedo "));
			}};
			bronze = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Heitor José Morais Lira Soares"));
				add(new CompetitorChampionOutput(1, "DAMAS", "Campina Grande", "Júlia Loes Barbosa Barros"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Pedro Sarmento Campelo"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Larissa Simões Peixoto"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Diego Silveira Rodrigues"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Antonio Da Costa França"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Ayla Vieira Moita"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Cecilia Ramos Gomes"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Larissa Terras Veras"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Vinicius Barbosa De Lima"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Maria Vitória Cavalcanti Figueiredo"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Leticia Rocha De Almeida"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Ana Catarina Souto De Oliveira"));
				add(new CompetitorChampionOutput(1, "DAMAS", "Campina Grande", "Jorge Miguel Da Costa Pinto"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Ana Luísa Barbosa De Mélo Araujo"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Larissa Mesquita Guerra Cavalcante De Oliveira"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "José Rodrigues Da Silva Neto"));
			}};
			honorableMention = new ArrayList<CompetitorChampionOutput>() {{
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Letícia Ribeiro Ventura Costa"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Livia Pessoa Lopes Da Silva"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Nicole Crispim Vasconcelos"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Alexandre Andrade De Farias"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Lucas Menezes Melo"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Ana Louyse Maciel Batista"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Alice Luna Pinto Dias"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Lucas Gomes Pires"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Maria Luisa Medeiros Nepomuceno Araujo"));
				add(new CompetitorChampionOutput(1, "Motiva", "Campina Grande", "Marcos Vinícius Antunes De Araújo"));
				add(new CompetitorChampionOutput(1, "GEO", "João Pessoa", "Joao Rafael Ponciano Da Costa"));
				add(new CompetitorChampionOutput(1, "IPEN", "Campina Grande", "Davi Santos Lisboa"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Júlia Cruz De Veras Souza"));
				add(new CompetitorChampionOutput(1, "Motiva", "João Pessoa", "Lorena De Britto Lyra Nóbrega"));
			}};
		}
		return new ResultCategoryOutput(gold, silver, bronze, honorableMention);
	}

}
