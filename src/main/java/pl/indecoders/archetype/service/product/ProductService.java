package pl.indecoders.archetype.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.Product;
import pl.indecoders.archetype.domain.product.ProductGroup;
import pl.indecoders.archetype.dto.product.NewProductAttributesDto;
import pl.indecoders.archetype.form.product.NewProductForm;
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
		return new NewProductAttributesDto(groupRepository.findByOwnerAndIsActive(owner, true));
	}
	
	public void persistProduct(NewProductForm form, final Account owner) {
		Product productToPersist = new Product();
		ProductGroup group = groupRepository.findByName(form.getGroup().getName());
		
		productToPersist.setOwner(owner);
		productToPersist.setBasePrice(form.getBasePrice());
		productToPersist.setProductName(form.getProductName());
		productToPersist.setProductSpecification(form.getProductSpecification());
		productToPersist.setGroup(group);
		productToPersist.setProductType(form.getProductType());
		productToPersist.setTaxAmount(form.getTaxAmount());
		productToPersist.setProductUnit(form.getProductUnit());
		productToPersist.setIsVisible(true);
		
		productRepository.save(productToPersist);
	}
}
