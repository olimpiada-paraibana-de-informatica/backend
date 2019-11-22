package br.edu.opi.manager.config;

import br.edu.opi.manager.olympiad.models.*;
import br.edu.opi.manager.olympiad.repositories.*;
import br.edu.opi.manager.person.models.Genre;
import br.edu.opi.manager.person.models.Person;
import br.edu.opi.manager.person.repositories.PersonRepository;
import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.project_patterns.models.user.Profile;
import br.edu.opi.manager.project_patterns.models.user.ProfileFactory;
import br.edu.opi.manager.project_patterns.repositories.ProfileRepository;
import br.edu.opi.manager.user.models.UserFactory;
import br.edu.opi.manager.user.models.UserModel;
import br.edu.opi.manager.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup;
	private UserRepository userRepository;
	private ProfileRepository profileRepository;
	private OpiDatesRepository opiDatesRepository;
	private ObiGraphRepository obiGraphRepository;
	private SbcGraphRepository sbcGraphRepository;
	private PersonRepository personRepository;
	private ObiChampionRepository obiChampionRepository;
	private InternationalChampionRepository internationalChampionRepository;

	@Autowired
	public InitialDataLoader(
			UserRepository userRepository,
			ProfileRepository profileRepository,
			OpiDatesRepository opiDatesRepository,
			ObiGraphRepository obiGraphRepository,
			SbcGraphRepository sbcGraphRepository,
			PersonRepository personRepository,
			ObiChampionRepository obiChampionRepository,
			InternationalChampionRepository internationalChampionRepository) {
		this.userRepository = userRepository;
		this.profileRepository = profileRepository;
		this.opiDatesRepository = opiDatesRepository;
		this.obiGraphRepository = obiGraphRepository;
		this.sbcGraphRepository = sbcGraphRepository;
		this.personRepository = personRepository;
		this.obiChampionRepository = obiChampionRepository;
		this.internationalChampionRepository = internationalChampionRepository;
		this.alreadySetup = false;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

		// Profile Delegate
		createProfileIfNotFound(ProfileFactory.delegateUser());

		// Profile Admin
		final Profile profile_admin = createProfileIfNotFound("Administrador", new HashSet<Privilege>() {
			private static final long serialVersionUID = -4532748588777883116L;

			{
				addAll(Arrays.asList(Privilege.values()));
			}
		});

		// Profile Test
		final Profile profile_teste = createProfileIfNotFound("Usuário Padrão", new HashSet<Privilege>() {
			private static final long serialVersionUID = -4532748588777883116L;

			{
				add(Privilege.C_US);
				add(Privilege.S_US);
				add(Privilege.I_US);
				add(Privilege.U_US);
			}
		});

		// User Admin
		UserModel user = UserFactory.createUserObject("administrador@email.com", "P@ssw0rd", "Administrador", "111.111.111-11", profile_admin);
		user.setNeedChangePassword(false);
		user = createUserIfNotFound(user);

		// User Test
		UserModel userTest = UserFactory.createUserObject("test@email.com", "P@ssw0rd", "Test", "986.863.610-80", profile_teste);
		userTest.setNeedChangePassword(true);
		userTest = createUserIfNotFound(userTest);

		createOpiDatesIfNotFound();

//		createObiChampionIfNotFound();
		createObiGraphIfNotFound();

		createSbcGraphIfNotFound();

		createInternationalChampionsIfNotFound();
	}

	private Profile createProfileIfNotFound(final String name, final Collection<Privilege> privileges) {
		Profile profile = profileRepository.findByName(name);
		if (profile == null) {
			profile = new Profile(name, new HashSet<>(privileges));
			profile = profileRepository.save(profile);
		}
		return profile;
	}

	private Profile createProfileIfNotFound(Profile profile) {
		return createProfileIfNotFound(profile.getName(), profile.getPrivileges());
	}

	private UserModel createUserIfNotFound(final UserModel userModel) {
		UserModel savedUser = userRepository.findByUsername(userModel.getUsername());
		if (savedUser == null) {
			savedUser = userRepository.save(userModel);
		}
		return savedUser;
	}

	private void createObiChampionIfNotFound() {
		persistObiChampion(new Person("Ramide Dantas", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2000, ObiAward.GOLD, ObiCategory.PROGRAMACAO_2, 1);
		persistObiChampion(new Person("Hugo Raniere", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2000, ObiAward.PARTICIPATION, ObiCategory.PROGRAMACAO_2, 12);
		persistObiChampion(new Person("Lincoln David Nery e Silva", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2001, ObiAward.GOLD, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("Lincoln David Nery e Silva", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2002, ObiAward.GOLD, ObiCategory.PROGRAMACAO_2, 8);
		persistObiChampion(new Person("Dennis Dantas de Souza", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.SILVER, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Dennis Dantas de Souza", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Dennis Dantas de Souza", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.SILVER, ObiCategory.PROGRAMACAO_JUNIOR, null);
		persistObiChampion(new Person("Letícia Maia Teixeira", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2007, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Letícia Maia Teixeira", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2008, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Lucas de Matos Figueiredo", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Lucas de Matos Figueiredo", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Lucas de Matos Figueiredo", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.HONORABLE_MENTION, ObiCategory.PROGRAMACAO_JUNIOR, null);
		persistObiChampion(new Person("Victor de Andrade de Almeida", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Victor de Andrade de Almeida", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2012, ObiAward.GOLD, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Victor de Andrade de Almeida", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2014, ObiAward.BRONZE, ObiCategory.PROGRAMACAO_SENIOR, null);
		persistObiChampion(new Person("Victor de Andrade de Almeida", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2015, ObiAward.BRONZE, ObiCategory.PROGRAMACAO_SENIOR, null);
		persistObiChampion(new Person("Mateus Carvalho Dantas", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Mateus Carvalho Dantas", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Mateus Carvalho Dantas", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.SILVER, ObiCategory.PROGRAMACAO_JUNIOR, null);
		persistObiChampion(new Person("Mateus Carvalho Dantas", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.GOLD, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Mateus Carvalho Dantas", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("João Luiz Carolino de Luna", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Victor Gonçalves Elias", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.GOLD, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Victor Gonçalves Elias", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.BRONZE, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Victor Gonçalves Elias", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.SILVER, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Victor Gonçalves Elias", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("Marina Gonçalves Assis", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2007, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Matheus Barbosa M. de Santos", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Manoel Lucas Urbano de Barros", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Manoel Lucas Urbano de Barros", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Manoel Lucas Urbano de Barros", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Manoel Lucas Urbano de Barros", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2012, ObiAward.GOLD, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("Alexandre Delgado Júnior", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Luno Rabay Coutinho", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Lucas Ribeiro de Andrade", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Lucas Ribeiro de Andrade", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2007, ObiAward.SILVER, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Gustavo Souto Henriques Campelo", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.GOLD, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Gustavo Souto Henriques Campelo", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Gustavo Souto Henriques Campelo", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.PROGRAMACAO_JUNIOR, null);
		persistObiChampion(new Person("Gustavo Souto Henriques Campelo", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2011, ObiAward.SILVER, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Gustavo Souto Henriques Campelo", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2012, ObiAward.GOLD, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Leticia Vanderlei Ferreira", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2008, ObiAward.SILVER, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Igor de C. Soares", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.SILVER, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Igor de C. Soares", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Renata Nunes Velloso", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2008, ObiAward.GOLD, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Daniel Abrantes Formiga", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Rémy de Fru", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Rémy de Fru", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2012, ObiAward.BRONZE, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("Abraão Aires Urquiza", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2012, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Luana Ferreira Leite de Araújo", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2008, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Felipe Abella", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.SILVER, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Felipe Abella", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.GOLD, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("Felipe Abella", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.GOLD, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("Felipe Abella", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2011, ObiAward.GOLD, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("Phyllipe César", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2008, ObiAward.BRONZE, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Phyllipe César", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.BRONZE, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("Phyllipe César", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.GOLD, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("Raphael Reichmann Rolim", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Raphael Reichmann Rolim", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2011, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Sofia Nunes P. de Oliveira", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2009, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Sofia Nunes P. de Oliveira", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Sofia Nunes P. de Oliveira", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2011, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Débora Nunes Pinto de Oliveira", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2009, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Débora Nunes Pinto de Oliveira", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Débora Nunes Pinto de Oliveira", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2012, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Lavínia Medeiros Silva", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2009, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Lavínia Medeiros Silva", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("José Vitor Figueirêdo Almeida", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.SILVER, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Rainan Osvaldo Carvalho Vieira", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.SILVER, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Rainan Osvaldo Carvalho Vieira", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Nicolas Eduardo da Fonseca Farias", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.GOLD, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Nicolas Eduardo da Fonseca Farias", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Wederson Santos Silva", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Luiz Almeida", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Camilla Matias de Azevedo", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2009, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Camilla Matias de Azevedo", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("João Nascimento", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Rafael Catalão Perrella", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2009, ObiAward.SILVER, ObiCategory.PROGRAMACAO_1, null);
		persistObiChampion(new Person("Rafael Catalão Perrella", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2011, ObiAward.SILVER, ObiCategory.PROGRAMACAO_2, null);
		persistObiChampion(new Person("João Pedro Maia Medeiros", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("João Pedro Maia Medeiros", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2011, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Bruna de Souza F. Torres", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Fabrício Martins Veras", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.BRONZE, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Ysrael Simões Lins de Oliveira", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Ysrael Simões Lins de Oliveira", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2011, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Marília Muniz Medeiros", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_1, null);
		persistObiChampion(new Person("Ana Clara Pereira Rolim", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Lucas Alcântara Pontes de Lemos", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Safire Torres", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Anna Julyana V. C. Brilhant", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Matheus Beltrão de Leon", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.BRONZE, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Joana L. de Carvalho", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Bruno Andreghetti Dantas", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Matheus Montenegro de Lacerda", LocalDate.of(2000, 1, 25), Genre.MASCULINO), 2010, ObiAward.SILVER, ObiCategory.INICIACAO_2, null);
		persistObiChampion(new Person("Iasmim Araújo Bandeira Mendes", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2010, ObiAward.SILVER, ObiCategory.PROGRAMACAO_JUNIOR, null);
		persistObiChampion(new Person("Maria Isabel Bezerra de Albuquerque", LocalDate.of(2000, 1, 25), Genre.FEMININO), 2011, ObiAward.HONORABLE_MENTION, ObiCategory.PROGRAMACAO_2, null);
	}

	private void persistObiChampion(Person person, Integer year, ObiAward obiAward, ObiCategory obiCategory, Integer position) {
		Person savedPerson = personRepository.findByFullNameEquals(person.getFullName());
		if (savedPerson != null) {
			person = savedPerson;
		}
		ObiChampion obiChampion = new ObiChampion(person, year, obiAward, obiCategory, position);
		obiChampionRepository.save(obiChampion);
	}

	private void createObiGraphIfNotFound() {
		List<ObiGraph> all = obiGraphRepository.findAll();
		if (all.isEmpty()) {
			all = new ArrayList<ObiGraph>() {{
				add(new ObiGraph(2018, 2, 6, 7, 13, ObiCategory.INICIACAO_JUNIOR));
				add(new ObiGraph(2019, 1, 2, 0, 3, ObiCategory.INICIACAO_JUNIOR));
				add(new ObiGraph(2000, 0, 0, 0, 0, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2001, 0, 0, 0, 0, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2002, 0, 0, 0, 0, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2003, 0, 0, 0, 0, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2004, 0, 0, 0, 0, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2005, 0, 0, 0, 0, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2006, 0, 0, 0, 0, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2007, 0, 1, 5, 0, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2008, 1, 0, 0, 2, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2009, 0, 0, 4, 2, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2010, 0, 1, 3, 4, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2011, 0, 1, 3, 0, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2012, 0, 2, 3, 1, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2013, 0, 3, 4, 3, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2014, 2, 2, 3, 4, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2015, 1, 1, 3, 6, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2016, 0, 4, 3, 2, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2017, 0, 1, 0, 1, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2018, 0, 1, 1, 5, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2019, 0, 1, 6, 0, ObiCategory.INICIACAO_1));
				add(new ObiGraph(2000, 0, 0, 0, 0, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2001, 0, 0, 0, 0, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2002, 0, 0, 0, 0, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2003, 0, 0, 0, 0, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2004, 0, 0, 0, 0, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2005, 0, 0, 0, 0, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2006, 0, 0, 0, 0, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2007, 1, 1, 3, 2, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2008, 1, 1, 6, 3, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2009, 1, 3, 1, 1, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2010, 0, 3, 3, 6, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2011, 0, 1, 1, 4, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2012, 0, 1, 2, 1, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2013, 1, 1, 2, 3, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2014, 2, 1, 4, 6, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2015, 2, 1, 5, 5, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2016, 1, 2, 1, 3, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2017, 0, 3, 1, 4, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2018, 1, 2, 2, 1, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2019, 0, 0, 0, 1, ObiCategory.INICIACAO_2));
				add(new ObiGraph(2000, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2001, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2002, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2003, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2004, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2005, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2006, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2007, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2008, 0, 0, 0, 1, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2009, 0, 1, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2010, 0, 1, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2011, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2012, 1, 2, 1, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2013, 0, 0, 1, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2014, 0, 0, 1, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2015, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2016, 0, 2, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2017, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2018, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2019, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_JUNIOR));
				add(new ObiGraph(2000, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2001, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2002, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2003, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2004, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2005, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2006, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2007, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2008, 0, 1, 2, 1, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2009, 0, 2, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2010, 1, 0, 0, 2, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2011, 0, 0, 0, 1, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2012, 2, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2013, 0, 0, 0, 2, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2014, 0, 0, 0, 2, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2015, 0, 1, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2016, 0, 1, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2017, 0, 0, 1, 2, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2018, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2019, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_1));
				add(new ObiGraph(2000, 1, 0, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2001, 1, 0, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2002, 1, 0, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2003, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2004, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2005, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2006, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2007, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2008, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2009, 1, 0, 1, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2010, 2, 1, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2011, 1, 0, 0, 2, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2012, 1, 0, 1, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2013, 0, 1, 0, 2, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2014, 0, 1, 0, 1, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2015, 0, 1, 0, 1, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2016, 0, 0, 0, 0, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2017, 0, 0, 0, 1, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2018, 0, 0, 0, 1, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2019, 0, 0, 1, 1, ObiCategory.PROGRAMACAO_2));
				add(new ObiGraph(2014, 1, 0, 1, 1, ObiCategory.PROGRAMACAO_SENIOR));
				add(new ObiGraph(2015, 1, 1, 1, 1, ObiCategory.PROGRAMACAO_SENIOR));
				add(new ObiGraph(2016, 2, 1, 0, 0, ObiCategory.PROGRAMACAO_SENIOR));
				add(new ObiGraph(2017, 2, 2, 1, 1, ObiCategory.PROGRAMACAO_SENIOR));
				add(new ObiGraph(2018, 4, 0, 1, 2, ObiCategory.PROGRAMACAO_SENIOR));
				add(new ObiGraph(2019, 0, 0, 2, 1, ObiCategory.PROGRAMACAO_SENIOR));
			}};
			obiGraphRepository.saveAll(all);
		}
	}

	private void createSbcGraphIfNotFound() {
		List<SbcGraph> all = sbcGraphRepository.findAll();
		if (all.isEmpty()) {
			all = new ArrayList<SbcGraph>() {{
				add(new SbcGraph(1996, 0, 0, 0, null, null, null));
				add(new SbcGraph(1997, 0, 0, 0, null, null, null));
				add(new SbcGraph(1998, 0, 0, 0, "UFPB-CG", "1", "14"));
				add(new SbcGraph(1999, 0, 0, 0, "UFPB-CG e UFPB-CG", "1 e 1", "19 e 20"));
				add(new SbcGraph(2000, 0, 0, 1, "UFPB-CG e UFPB-CG", "2 e 2", "8 e 13"));
				add(new SbcGraph(2001, 0, 0, 0, "UFPB-CG", "2", "26"));
				add(new SbcGraph(2002, 0, 0, 0, "UFCG", "3", null));
				add(new SbcGraph(2003, 0, 0, 0, "UFCG, UFCG, UFCG", "3, 3 e 2", "11, 13 e 18"));
				add(new SbcGraph(2004, 0, 0, 0, "UFCG", "2", "25"));
				add(new SbcGraph(2005, 0, 0, 0, null, null, null));
				add(new SbcGraph(2006, 0, 0, 0, "UFCG", "3", "15"));
				add(new SbcGraph(2007, 0, 0, 0, "UFCG", "2", "34"));
				add(new SbcGraph(2008, 0, 0, 0, "UFCG", "3", "32"));
				add(new SbcGraph(2009, 0, 0, 0, "UFCG", "7", "17"));
				add(new SbcGraph(2010, 0, 0, 0, "UFCG", "5", "23"));
				add(new SbcGraph(2011, 1, 0, 0, "UFCG", "7", "3"));
				add(new SbcGraph(2012, 0, 0, 0, "UFCG", "4", "17"));
				add(new SbcGraph(2013, 0, 0, 1, "UFCG e UFCG", "5 e 4", "7 e 12"));
				add(new SbcGraph(2014, 1, 0, 0, "UFCG", "8", "2"));
				add(new SbcGraph(2015, 1, 0, 0, "UFCG, UFCG, IFPB e UFPB-JP", "10, 4, 3 e 3", "2, 25, 37 e 40"));
				add(new SbcGraph(2016, 0, 1, 0, "UFCG, IFPB e UFPB-JP", "8, 3 e 0", "6, 36 e 59"));
				add(new SbcGraph(2017, 0, 1, 1, "UFCG, UFCG, UFPB-JP e IFPB", "8, 8, 2, 2", "5, 7, 53, 55"));
				add(new SbcGraph(2018, 1, 0, 0, "UFCG, IFPB", "9, 4", "3, 29"));
				add(new SbcGraph(2019, 0, 0, 0, "UFCG, UFCG, IFPB e UniFacisa", "5, 5, 3, 1", "17, 22, 41, 53"));
			}};
			sbcGraphRepository.saveAll(all);
		}
	}

	private void createOpiDatesIfNotFound() {
		boolean exists = opiDatesRepository.existsById(OpiDates.OPI_DATES_ID);
		if (!exists) {
			OpiDates opiDates = new OpiDates(
					"28/04/2019",
					"04/05/2019 (sábado pela manhã) - Duração: 1:30",
					"Cada escola definirá o horário - 02/05/2019 (quinta) - Duração: 1:30",
					"Cada escola definirá o horário - 02/05/2019 (quinta) - Duração: 2:30",
					"31/08/2019 (sábado) - 08:00 até às 09:30 (cada colégio organizará a 2a fase)",
					"31/08/2019 (sábado) - 08:00 até às 10:30 (cada colégio organizará a 2a fase)",
					"Outubro de 2019\n",
					"Ocorrerá no teatro da UniFacisa às 17 horas no dia 11/11",
					"Até o dia 28/07/2019",
					"03/08/2019 - 14:00 até às 19:00",
					"Setembro de 2019",
					"Ocorrerá no teatro da UniFacisa às 17 horas no dia 11/11");
			opiDates.setId(OpiDates.OPI_DATES_ID);
			opiDatesRepository.save(opiDates);
		}
	}

	private void createInternationalChampionsIfNotFound() {
		List<InternationalChampion> all = internationalChampionRepository.findAll();
		if (all.isEmpty()) {
			all = new ArrayList<InternationalChampion>() {{
				add(new InternationalChampion(2000, "Ramide Dantas", "CEFET", "Participação na Olimpíada Internacional de Informática na China"));
				add(new InternationalChampion(2001, "Lincoln David Nery e Silva", "CEFET", "Participação na Olimpíada Internacional de Informática na Finlândia"));
				add(new InternationalChampion(2009, "Felipe Abella", "GEO", "Medalha de prata na Olimpíada Internacional de Informática na Bulgária"));
				add(new InternationalChampion(2010, "Felipe Abella", "GEO", "Medalha de prata na Olimpíada Internacional de Informática no Canadá"));
				add(new InternationalChampion(2011, "Felipe Abella", "UFCG", "Medalha de ouro na Olimpíada Internacional de Informática na Tailândia (1a medalha de ouro do Brasil)"));
				add(new InternationalChampion(2012, "Diogo Anderson, Felipe Abella, Phyllipe César", "UFCG", "Campeão Latino Americano na fase mundial da ACM ICPC na Polônia"));
				add(new InternationalChampion(2013, "Mateus Dantas", "UFCG", "Participação na Olimpíada Internacional de Informática na Austrália"));
				add(new InternationalChampion(2013, "Mateus Dantas", "UFCG", "Medalha de ouro na Olimpíada Iberoamericana de Informática"));
				add(new InternationalChampion(2014, "Danilo Pimenteira, Mateus Dantas, Phyllipe César", "UFCG", "Menção Honrosa na fase mundial da ACM ICPC na Rússia"));
				add(new InternationalChampion(2015, "Lucas de Matos, Manoel Urbano, Rafael Perrella", "UFCG", "Menção Honrosa na fase mundial da ACM ICPC no Marrocos"));
				add(new InternationalChampion(2016, "Árysson Cavalvanti, Felipe Abella, Manoel Urbano", "UFCG", "44a colocação na fase mundial da ACM ICPC na Tailândia (3o lugar na América Latina)"));
				add(new InternationalChampion(2017, "Lucas de Matos, Ordan Santos, Victor Almeida", "UFCG", "Menção Honrosa na fase mundial da ACM ICPC nos Estados Unidos"));
				add(new InternationalChampion(2017, "Rohit Gheyi", "UFCG", "Coach Award na fase mundial da ACM ICPC"));
				add(new InternationalChampion(2018, "Árysson Cavalvanti, Felipe Mota, Ordan Santos", "UFCG", "Menção Honrosa na fase mundial da ACM ICPC nos Estados Unidos"));
				add(new InternationalChampion(2019, "Arthur Rodrigues, Emeson Lucena, Gustavo Ribeiro", "UFCG", "Menção Honrosa na fase mundial da ACM ICPC nos Estados Unidos"));
			}};
			internationalChampionRepository.saveAll(all);
		}
	}

}
