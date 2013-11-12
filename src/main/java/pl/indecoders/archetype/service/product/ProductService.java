package pl.indecoders.archetype.service.product;

import java.util.List;
import static pl.indecoders.archetype.utils.SortTranslationUtils.translateDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	
	public void removeProduct(Long id) {
		Product productToRemove = productRepository.findOne(id);
		
		productToRemove.setIsVisible(false);
		
		productRepository.save(productToRemove);
	}
	
	public List<Product> getProductsPerPage(final int page, final int size, final Account owner) {
		PageRequest req = new PageRequest(page, size);
		
		return productRepository.findByOwnerAndIsVisible(req, owner, true);
	}
	
	public List<Product> getSortedAndPagedResults(final int page, final int size, final Account owner, String sortField, String sortDir) {
		PageRequest req = new PageRequest(page, size, translateDirection(sortDir), sortField);
		
		return productRepository.findByOwnerAndIsVisible(req, owner, true);
	}
	
	public NewProductForm prepareEditForm(Long id) {
		NewProductForm editForm = new NewProductForm();
		Product productToEdit = productRepository.findOne(id);
		
		editForm.setBasePrice(productToEdit.getBasePrice());
		editForm.setGroup(productToEdit.getGroup());
		editForm.setProductName(productToEdit.getProductName());
		editForm.setProductSpecification(productToEdit.getProductSpecification());
		editForm.setProductType(productToEdit.getProductType());
		editForm.setProductUnit(productToEdit.getProductUnit());
		editForm.setTaxAmount(productToEdit.getTaxAmount());
		
		
		return editForm;
	}
	
	public void persistProduct(NewProductForm form, final Account owner) {
		Product productToPersist = new Product();
		ProductGroup group = groupRepository.findByNameAndIsActive(form.getGroup().getName(), true);
		
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
