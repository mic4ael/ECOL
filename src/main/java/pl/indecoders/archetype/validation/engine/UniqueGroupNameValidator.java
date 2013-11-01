package pl.indecoders.archetype.validation.engine;

import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.ProductGroup;
import pl.indecoders.archetype.repository.product.ProductGroupRepository;
import pl.indecoders.archetype.validation.annotation.UniqueGroupName;

/**
 * The Class UniqueGroupName.
 * @author Mateusz
 */
@Component
public class UniqueGroupNameValidator implements ConstraintValidator<UniqueGroupName, String> {

	@Autowired
	private ProductGroupRepository productGroupRepository;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public void initialize(UniqueGroupName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Account currentlySigned = (Account) session.getAttribute(CURRENTLY_SIGNED);
		
		System.out.println("ASDASDASD" + currentlySigned);
		
		ProductGroup group = productGroupRepository.findByOwnerAndName(currentlySigned, value);
		return group == null;
	}
}
