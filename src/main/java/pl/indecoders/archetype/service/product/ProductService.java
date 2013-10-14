package pl.indecoders.archetype.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.dto.product.NewProductAttributesDto;
import pl.indecoders.archetype.repository.product.ProductGroupRepository;
import pl.indecoders.archetype.repository.product.ProductRepository;

/**
 * The Class ProductService.
 * @author Mateusz
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductGroupRepository groupRepository;
	
	public NewProductAttributesDto prepareNewProductsAttributes(final Account owner) {
		return new NewProductAttributesDto(groupRepository.findByOwner(owner));
	}
}
