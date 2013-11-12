package pl.indecoders.archetype.specification.customer;

import static pl.indecoders.archetype.utils.LikeBuildUtils.prepareLikeClause;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.address.Address_;
import pl.indecoders.archetype.domain.customer.Customer;
import pl.indecoders.archetype.domain.customer.Customer_;
import pl.indecoders.archetype.form.customer.InvoiceNewCustomerForm;


/**
 * The Class CustomerSpecifications.
 * @author Mateusz
 */
public class CustomerSpecifications {

	public static Specification<Customer> hasOwnerAndFiltered(final Account owner, final String pattern) {
		return new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate hasOwner = cb.equal(root.get(Customer_.owner), owner);
				
				Predicate isVisible = cb.equal(root.get(Customer_.isVisible), true);
				
				Predicate containsName = cb.like(cb.lower(root.get(Customer_.name)), prepareLikeClause(pattern));
				Predicate containsNip = cb.like(cb.lower(root.get(Customer_.nip)), prepareLikeClause(pattern));
				Predicate containsCity = cb.like(cb.lower(root.get(Customer_.address).get(Address_.city)), prepareLikeClause(pattern));
				
				Predicate containsAltern = cb.or(containsName, containsNip, containsCity);
				return cb.and(hasOwner, isVisible, containsAltern);
			}
		};
	}
	
	public static Specification<Customer> hasGivenProperties(final Account owner, final InvoiceNewCustomerForm form) {
		return new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate hasOwner = cb.equal(root.get(Customer_.owner), owner);
				Predicate hasName = cb.equal(root.get(Customer_.name), form.getName());
				Predicate hasNip = cb.equal(root.get(Customer_.nip), form.getNip());
				Predicate isVisible = cb.equal(root.get(Customer_.isVisible), true);
				return cb.and(hasOwner, hasName, hasNip, isVisible);
			}
		};
	}
}
