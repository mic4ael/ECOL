package pl.indecoders.archetype.conversion;

import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.ProductGroup;
import pl.indecoders.archetype.repository.product.ProductGroupRepository;

/**
 * The Class ProductGroupConverter.
 * @author Mateusz
 */
@Component
public class ProductGroupConverter implements Converter<String, ProductGroup> {

	@Autowired
	private ProductGroupRepository productGroupRepository;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public ProductGroup convert(String source) {
		for(ProductGroup group : productGroupRepository.findByOwner((Account) session.getAttribute(CURRENTLY_SIGNED))) {
			if(group.getName().equalsIgnoreCase(source)) {
				return group;
			}
		}
		return null;
	}
}
