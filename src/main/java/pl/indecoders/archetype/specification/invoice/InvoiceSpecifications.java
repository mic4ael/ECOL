package pl.indecoders.archetype.specification.invoice;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.Specification;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.document.Invoice;
import pl.indecoders.archetype.domain.document.Invoice_;

/**
 * The Class InvoiceSpecifications.
 * @author Mateusz
 */
public class InvoiceSpecifications {

	public static Specification<Invoice> hasOwnerAndInterval(final Account owner, final DateTime dateFrom, final DateTime dateTo) {
		return new Specification<Invoice>() {
			@Override
			public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate hasOwner = cb.equal(root.get(Invoice_.owner), owner);
				Predicate hasDateFrom = cb.greaterThanOrEqualTo(root.get(Invoice_.soldDate), dateFrom);
				Predicate hasDateTo = cb.lessThanOrEqualTo(root.get(Invoice_.soldDate), dateTo);
				return cb.and(hasOwner, hasDateFrom, hasDateTo);
			}
		};
	}
}
