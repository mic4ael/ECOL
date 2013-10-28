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
	
	public ProductGroup findByOwnerAndName(final Account owner, final String name);
	public List<ProductGroup> findByOwner(final Account owner);
	public List<ProductGroup> findByOwner(final Pageable req, final Account owner);
	public ProductGroup findByName(final String name);
	
	@Query("UPDATE ProductGroup p set p.name=?2, p.specification=?3 WHERE p.id=?1")
	public void updateProductGroup(final Long id, final String name, final String spec);
	
	@Query("select count(p) from ProductGroup p where p.owner = ?1")
	public Long countByOwner(final Account owner);
}
