package pl.indecoders.archetype.specification.product;

import static pl.indecoders.archetype.utils.LikeBuildUtils.prepareLikeClause;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.product.Product;
import pl.indecoders.archetype.domain.product.Product_;

/**
 * The Class ProductSpecifications.
 * @author Mateusz
 */
public class ProductSpecifications {

	public static Specification<Product> containsPattern(final String pattern, final Account owner) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate hasOwner = cb.equal(root.get(Product_.owner), owner);
				Predicate isVisible = cb.equal(root.get(Product_.isVisible), true);
				Predicate containsPattern = cb.like(cb.lower(root.get(Product_.productName)), prepareLikeClause(pattern));
				
				return cb.and(hasOwner, isVisible, containsPattern);
			}
		};
	}
}
