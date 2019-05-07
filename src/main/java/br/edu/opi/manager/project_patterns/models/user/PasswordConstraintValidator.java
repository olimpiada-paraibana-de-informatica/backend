package br.edu.opi.manager.project_patterns.models.user;

import br.edu.opi.manager.utils.ErrorMessagesConstants;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		PasswordValidator validator = new PasswordValidator(Arrays.asList(
				new LengthRule(6, 30),
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				new CharacterRule(EnglishCharacterData.LowerCase, 1),
				new CharacterRule(EnglishCharacterData.Digit, 1),
				new CharacterRule(EnglishCharacterData.Special, 1),
				new WhitespaceRule()));

		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		}

		context.buildConstraintViolationWithTemplate(ErrorMessagesConstants.PASSWORD_INVALID).addConstraintViolation();

		return false;
	}

}
