package pl.indecoders.archetype.repository.product;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.Product;

/**
 * The Interface ProductRepository.
 * @author Mateusz
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
	public List<Product> findByOwner(final Account owner);
	
	@Query("select count(p) from Product p where p.owner = ?1")
	public Long countByOwner(final Account owner);
}
