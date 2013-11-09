package pl.indecoders.archetype.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import static pl.indecoders.archetype.utils.SortTranslationUtils.translateDirection;
import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.Product;
import pl.indecoders.archetype.domain.product.ProductGroup;
import pl.indecoders.archetype.form.product.EditionProductGroupForm;
import pl.indecoders.archetype.form.product.NewProductGroupForm;
import pl.indecoders.archetype.repository.product.ProductGroupRepository;
import pl.indecoders.archetype.repository.product.ProductRepository;

/**
 * The Class ProductGroupService.
 * @author Mateusz
 */
@Service
public class ProductGroupService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductGroupRepository productGroupRepository;
	
	public void persistProductGroup(final NewProductGroupForm form, final Account owner) {
		ProductGroup group = new ProductGroup();
		group.setName(form.getName());
		group.setSpecification(form.getSpecification() != null ? form.getSpecification() : null);
		group.setOwner(owner);
		group.setIsActive(true);
		
		productGroupRepository.save(group);
	}
	
	private void removeProducts(ProductGroup group) {
		List<Product> productsToRemove = productRepository.findByGroupAndIsVisible(group, true);
		
		for(Product p : productsToRemove) {
			p.setIsVisible(false);
		}
			
		productRepository.save(productsToRemove);
	}
	
	public void removeProductGroup(final Long id) {
		ProductGroup groupToRemove = productGroupRepository.findOne(id);
		
		removeProducts(groupToRemove);
		
		groupToRemove.setIsActive(false);
		productGroupRepository.save(groupToRemove);
	}
	
	public List<ProductGroup> getProductGroupsPerPage(final Account owner, final int pageIndex, final int itemsPerPage) {
		final PageRequest request = new PageRequest(pageIndex, itemsPerPage);
		return productGroupRepository.findByOwnerAndIsActive(request, owner, true);
	}
	
	public List<ProductGroup> getSortedAndPagedProducts(final Account owner, final int pageIndex, final int perPage, final String sortDir, final String sortField) {
		final PageRequest req = new PageRequest(pageIndex, perPage, translateDirection(sortDir), sortField);
		
		return productGroupRepository.findByOwnerAndIsActive(req, owner, true);
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
