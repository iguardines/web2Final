package ar.edu.uces.progweb2.booksmov.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.booksmov.dto.UserDto;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.user.name.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.user.password.empty");
	}

}
