package pl.indecoders.archetype.validation.engine;

import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_EDITED_GROUP_ID;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.ProductGroup;
import pl.indecoders.archetype.repository.product.ProductGroupRepository;
import pl.indecoders.archetype.security.SecurityUserContext;
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
	
	@Autowired
	private SecurityUserContext userContext;
	
	@Override
	public void initialize(UniqueGroupName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Account currentlySigned = userContext.getSignedUser();
		ProductGroup group = productGroupRepository.findByOwnerAndNameAndIsActive(currentlySigned, value, true);
		Long id = (Long) session.getAttribute(CURRENTLY_EDITED_GROUP_ID);
		
		if (group != null && id != null)
			return (group.getId().equals(id) ? true : (group.getName().equals(value) ? false : true));
		
		return group == null;
	}
}
