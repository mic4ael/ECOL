package pl.indecoders.archetype.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.ProductGroup;

/**
 * The Class ProductGroupRepository.
 * @author Mateusz
 */
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
	
	public ProductGroup findByOwnerAndName(final Account owner, final String name);
	public List<ProductGroup> findByOwner(final Account owner);
}
