package pl.indecoders.archetype.validation.engine;

import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.repository.customer.CustomerRepository;
import pl.indecoders.archetype.validation.annotation.UniqueNip;

/**
 * The Class UniqueCustomerNameValidator.
 * @author Mateusz
 */
@Component
public class UniqueNipValidator implements ConstraintValidator<UniqueNip, String> {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public void initialize(UniqueNip constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return customerRepository.findByOwnerAndNipAndIsVisible((Account) session.getAttribute(CURRENTLY_SIGNED), value, true) == null;
	}
}
