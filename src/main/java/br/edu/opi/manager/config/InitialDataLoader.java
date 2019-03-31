package br.edu.opi.manager.config;

import br.edu.opi.manager.conventions.models.user.Privilege;
import br.edu.opi.manager.conventions.models.user.Profile;
import br.edu.opi.manager.conventions.models.user.ProfileRepository;
import br.edu.opi.manager.user.model.UserFactory;
import br.edu.opi.manager.user.model.UserModel;
import br.edu.opi.manager.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProfileRepository profileRepository;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

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
				add(Privilege.D_US);
				add(Privilege.R_US);
			}
		});

		// User Admin
		UserModel user = UserFactory.createUserObject("administrador@email.com", "P@ssw0rd", "Administrador",
				"Geral", "111.111.111-11", profile_admin);
		user.setNeedChangePassword(false);
		user = createUserIfNotFound(user);

		// User Test
		UserModel userTest = UserFactory.createUserObject("test@email.com", "P@ssw0rd", "Test", "Geral",
				"986.863.610-80", profile_teste);
		userTest.setNeedChangePassword(false);
		userTest = createUserIfNotFound(userTest);

		}

		private Profile createProfileIfNotFound ( final String name, final Collection<Privilege> privileges){
			Profile profile = profileRepository.findByName(name);
			if (profile == null) {
				profile = new Profile(name, new HashSet<>(privileges));
				profile = profileRepository.save(profile);
			}
			return profile;
		}

		private UserModel createUserIfNotFound ( final UserModel userModel){
			UserModel savedUser = userRepository.findByUsername(userModel.getUsername());
			if (savedUser == null) {
				savedUser = userRepository.save(userModel);
			}
			return savedUser;
		}

}