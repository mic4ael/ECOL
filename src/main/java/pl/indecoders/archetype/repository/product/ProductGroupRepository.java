package pl.indecoders.archetype.repository.product;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.ProductGroup;

/**
 * The Class ProductGroupRepository.
 * @author Mateusz
 */
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long>, JpaSpecificationExecutor<ProductGroup> {
	
	public ProductGroup findByOwnerAndNameAndIsActive(final Account owner, final String name, final boolean isActive);
	public List<ProductGroup> findByOwnerAndIsActive(final Account owner, boolean isActive);
	public List<ProductGroup> findByOwnerAndIsActive(final Pageable req, final Account owner, final boolean isActive);
	public ProductGroup findByName(final String name);
	
	@Query("select count(p) from ProductGroup p where p.owner = ?1 and p.isActive = ?2")
	public Long countByOwnerAndIsActive(final Account owner, final boolean isActive);
}
