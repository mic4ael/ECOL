package pl.indecoders.archetype.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.Product;
import pl.indecoders.archetype.domain.product.ProductGroup;

/**
 * The Interface ProductRepository.
 * @author Mateusz
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
	public List<Product> findByOwner(final Account owner);
	public List<Product> findByOwnerAndIsVisible(final Account owner, final boolean isVisible);
	public List<Product> findByGroupAndIsVisible(final ProductGroup group, final boolean isVisible);
	@Query("select count(p) from Product p where p.owner = ?1")
	public Long countByOwner(final Account owner);
}
