package pl.indecoders.archetype.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.Product;

/**
 * The Interface ProductRepository.
 * @author Mateusz
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	public List<Product> findByOwner(final Account owner);
}
