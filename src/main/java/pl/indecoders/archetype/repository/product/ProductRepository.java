package pl.indecoders.archetype.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.indecoders.archetype.domain.product.Product;

/**
 * The Interface ProductRepository.
 * @author Mateusz
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
