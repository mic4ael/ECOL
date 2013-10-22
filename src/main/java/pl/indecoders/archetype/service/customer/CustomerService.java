package pl.indecoders.archetype.service.customer;

import static pl.indecoders.archetype.utils.SortTranslationUtils.translateDirection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.address.Address;
import pl.indecoders.archetype.domain.customer.Customer;
import pl.indecoders.archetype.form.customer.NewCustomerForm;
import pl.indecoders.archetype.repository.customer.CustomerRepository;

/**
 * The Class CustomerService.
 * @author Mateusz
 */
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public void processCustomer(final NewCustomerForm form, final Account owner) {
		Customer customer = new Customer();
		customer.setOwner(owner);
		customer.setName(form.getName());
		customer.setNip(form.getNip());
		customer.setAddress(createAddress(form));
		customer.setContactPhone(form.getContactPhone() != null ? form.getContactPhone() : null);
		customer.setFaxPhone(form.getFaxPhone() != null ? form.getFaxPhone() : null);
		customer.setEmail(form.getEmail() != null ? form.getEmail() : null);
		customer.setIsVisible(true);
		customerRepository.save(customer);
	}

	private Address createAddress(NewCustomerForm form) {
		Address address = new Address();
		address.setCity(form.getCity());
		address.setPostalCode(form.getPostalCode());
		address.setHomeNumber(form.getHomeNumber());
		address.setStreet(form.getStreet());
		return address;
	}
	
	public List<Customer> getPagedCustomers(final Account owner, final Integer pageIndex, final Integer pageLimit) {
		final PageRequest request = new PageRequest(pageIndex, pageLimit);
		return customerRepository.findByOwnerAndIsVisible(request, owner, true);
	}
	
	public List<Customer> getPagedCustomers(final Account owner, final Integer pageIndex, final Integer pageLimit, final String sortProperty, final String sortDirection) {
		final PageRequest request = new PageRequest(pageIndex, pageLimit, translateDirection(sortDirection), sortProperty);
		return customerRepository.findByOwnerAndIsVisible(request, owner, true);
	}
	
	public void setCustomerNotVisible(final Long id) {
		final Customer customer = customerRepository.findOne(id);
		customer.setIsVisible(false);
		customerRepository.save(customer);
	}
}
