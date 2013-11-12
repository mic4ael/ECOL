package pl.indecoders.archetype.validation.engine;

import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_EDITED_PRODUCT_ID;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.Product;
import pl.indecoders.archetype.repository.product.ProductRepository;
import pl.indecoders.archetype.security.SecurityUserContext;
import pl.indecoders.archetype.validation.annotation.UniqueProductName;

@Component
public class UniqueProductNameValidator implements ConstraintValidator <UniqueProductName, String> {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SecurityUserContext userContext;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public void initialize(UniqueProductName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Account currentlySigned = userContext.getSignedUser();
		Product product = productRepository.findByOwnerAndProductNameAndIsVisible(currentlySigned, value, true);
		Long id = (Long) session.getAttribute(CURRENTLY_EDITED_PRODUCT_ID);
		
		if (product != null && id != null)
			return (product.getId().equals(id) ? true : (product.getProductName().equals(value) ? false : true));
		
		return product == null;
	}
}
