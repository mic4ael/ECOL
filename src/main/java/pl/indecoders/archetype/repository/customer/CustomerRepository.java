package pl.indecoders.archetype.repository.customer;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.customer.Customer;

/**
 * The Class CustomerRepository.
 * @author Mateusz
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
	
	@Query("select count(c) from Customer c where c.owner = ?1 and isVisible = true")
	public Long countByOwner(final Account owner);
	
	public List<Customer> findByOwnerAndIsVisible(Pageable pageable, final Account owner, final Boolean isVisible);
	public Customer findByOwnerAndNameAndIsVisible(final Account owner, final String name, final Boolean isVisible);
}
