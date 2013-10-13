package pl.indecoders.archetype.validation.engine;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.repository.accout.AccountRepository;
import pl.indecoders.archetype.validation.annotation.UniqueEmail;

/**
 * The Class UniqueEmailValidator.
 * @author Mateusz
 */

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public void initialize(UniqueEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return accountRepository.findByEmail(email) == null ? true : false;
	}
}
