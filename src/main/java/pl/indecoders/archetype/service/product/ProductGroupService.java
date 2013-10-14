package pl.indecoders.archetype.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.ProductGroup;
import pl.indecoders.archetype.form.product.EditionProductGroupForm;
import pl.indecoders.archetype.form.product.NewProductGroupForm;
import pl.indecoders.archetype.repository.product.ProductGroupRepository;

/**
 * The Class ProductGroupService.
 * @author Mateusz
 */
@Service
public class ProductGroupService {

	@Autowired
	private ProductGroupRepository productGroupRepository;
	
	public void persistProductGroup(final NewProductGroupForm form, final Account owner) {
		ProductGroup group = new ProductGroup();
		group.setName(form.getName());
		group.setSpecification(form.getSpecification() != null ? form.getSpecification() : null);
		group.setOwner(owner);
		productGroupRepository.save(group);
	}
	
	public void removeProductGroup(final Long id) {
		productGroupRepository.delete(id);
	}
	
	public void editProductGroup(final Long id, final EditionProductGroupForm form) {
		ProductGroup group = productGroupRepository.findOne(id);
		group.setName(form.getName());
		if(form.getSpecification() != null) {
			group.setSpecification(form.getSpecification());
		}
		productGroupRepository.save(group);
	}
}
