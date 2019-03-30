package br.edu.opi.manager.user.dto;

import br.edu.opi.manager.conventions.models.Profile;
import br.edu.opi.manager.user.model.UserFactory;
import br.edu.opi.manager.user.model.UserModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Mapper to User.
 */
@Component("userIO")
public class UserIO {

	private ModelMapper modelMapper;

	final Converter<UserInput, UserModel> userConverter = new Converter<UserInput, UserModel>() {

		@Override
		public UserModel convert(MappingContext<UserInput, UserModel> context) {
			UserInput userInput = context.getSource();
			// @formatter:off
			return UserFactory.createUserObject(
					userInput.getUsername(),
					userInput.getPassword(),
					userInput.getFirstName(),
					userInput.getLastName(),
					userInput.getCpf(),
					new Profile(userInput.getProfileId()));
			// @formatter:on
		}
	};

	public UserIO() {
		modelMapper = new ModelMapper();
		modelMapper.addConverter(userConverter);
	}

	public UserModel mapTo(UserInput userInput) {
		return this.modelMapper.map(userInput, UserModel.class);
	}

}
