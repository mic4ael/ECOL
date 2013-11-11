package pl.indecoders.archetype.controller.customer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;
import static pl.indecoders.archetype.navigation.Navigator.CUSTOMERS_LIST_PATH;
import static pl.indecoders.archetype.navigation.Navigator.CUSTOMERS_LIST_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.CUSTOMER_COUNT_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.CUSTOMER_LIST_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.CUSTOMER_LIST_DIR_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.CUSTOMER_LIST_PAGES_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.CUSTOMER_LIST_SORT_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.EDITED_CUSTOMER_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.EDIT_CUSTOMER_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.GET_CUSTOMERS_JSON;
import static pl.indecoders.archetype.navigation.Navigator.GET_PROPER_CUSTOMER_JSON;
import static pl.indecoders.archetype.navigation.Navigator.IS_CUSTOMER_AVAILABLE;
import static pl.indecoders.archetype.navigation.Navigator.NEW_CUSTOMER_FORM_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.NEW_CUSTOMER_PATH;
import static pl.indecoders.archetype.navigation.Navigator.NEW_CUSTOMER_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.OPERATION_SUCCESS;
import static pl.indecoders.archetype.specification.customer.CustomerSpecifications.hasOwnerAndFiltered;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.indecoders.archetype.domain.customer.Customer;
import pl.indecoders.archetype.form.customer.NewCustomerForm;
import pl.indecoders.archetype.repository.customer.CustomerRepository;
import pl.indecoders.archetype.security.SecurityUserContext;
import pl.indecoders.archetype.service.customer.CustomerService;
import pl.indecoders.archetype.utils.PaginationUtils;

/**
 * The Class CustomerController.
 * 
 * @author Mateusz
 */
@Controller
public class CustomerController {

	private static final String OPERATION_SUCCESS_MESSAGE = "operation.success";
	private static final Integer RESULTS_ON_PAGE = 10;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private SecurityUserContext userContext;

	@Autowired
	private MessageSource messageSource;

	/* Attributes */
	
	@ModelAttribute(NEW_CUSTOMER_FORM_ATTRIBUTE)
	public NewCustomerForm newCustomerForm() {
		return new NewCustomerForm();
	}

	@ModelAttribute(CUSTOMER_COUNT_ATTRIBUTE)
	public Long customersCount(final HttpSession session) {
		return customerRepository.countByOwner(userContext.getSignedUser());
	}
	
	@ModelAttribute(CUSTOMER_LIST_PAGES_ATTRIBUTE)
    public Integer amountOfPages() {
            return new PaginationUtils(RESULTS_ON_PAGE).numberOfPages(customerRepository.countByOwner(userContext.getSignedUser()));
    }

	/* New customer */

	@RequestMapping(value = NEW_CUSTOMER_PATH, method = GET)
	public String showNewCustomerPage(final HttpSession session) {
		session.setAttribute(CURRENTLY_SIGNED, userContext.getSignedUser());
		return NEW_CUSTOMER_VIEW;
	}

	@RequestMapping(value = NEW_CUSTOMER_PATH, method = POST)
	public String processNewCustomerPage(@Valid @ModelAttribute(NEW_CUSTOMER_FORM_ATTRIBUTE) NewCustomerForm form, final BindingResult results, final Model model, final Locale locale) {
		if(results.hasErrors()) {
			return NEW_CUSTOMER_VIEW;
		}
		customerService.processCustomer(form, userContext.getSignedUser());
		model.addAttribute(OPERATION_SUCCESS, messageSource.getMessage(OPERATION_SUCCESS_MESSAGE, null, locale));
		model.addAttribute(NEW_CUSTOMER_FORM_ATTRIBUTE, new NewCustomerForm());
		return NEW_CUSTOMER_VIEW;
	}

	/* List of customers */

	@RequestMapping(value = CUSTOMERS_LIST_PATH + "/{page}", method = GET)
	public String showCustomersListPage(final Model model, @PathVariable Integer page, final HttpSession session) {

		if(session.getAttribute(CUSTOMER_LIST_SORT_ATTRIBUTE) == null) {
			model.addAttribute(CUSTOMER_LIST_ATTRIBUTE, customerService.getPagedCustomers(userContext.getSignedUser(), page-1, RESULTS_ON_PAGE));
			return CUSTOMERS_LIST_VIEW;
		}
		model.addAttribute(CUSTOMER_LIST_ATTRIBUTE, customerService.getPagedCustomers(userContext.getSignedUser(), page-1, RESULTS_ON_PAGE,
				(String) session.getAttribute(CUSTOMER_LIST_SORT_ATTRIBUTE), (String) session.getAttribute(CUSTOMER_LIST_DIR_ATTRIBUTE)));
		return CUSTOMERS_LIST_VIEW;
	}
	
	@RequestMapping(value = CUSTOMERS_LIST_PATH + "/{page}", method = POST)
	public String sortCustomersListPage(final HttpSession session, final Model model, @PathVariable Integer page, @RequestParam("dir") String direction, @RequestParam("property") String property) {
		session.setAttribute(CUSTOMER_LIST_SORT_ATTRIBUTE, property);
		session.setAttribute(CUSTOMER_LIST_DIR_ATTRIBUTE, direction);
		model.addAttribute(CUSTOMER_LIST_ATTRIBUTE, customerService.getPagedCustomers(userContext.getSignedUser(), page-1, RESULTS_ON_PAGE, property, direction));
		return CUSTOMERS_LIST_VIEW;
	}
	
	/* Checks if given name is available */
	
	@ResponseBody
	@RequestMapping(value = IS_CUSTOMER_AVAILABLE, method = POST)
	public boolean isCustomerNameAvailable(@RequestBody String name) {
		return customerRepository.findByOwnerAndNameAndIsVisible(userContext.getSignedUser(), name, true) == null ? true : false;
	}
	
	/* Removes a customer */
	
	@RequestMapping(value = CUSTOMERS_LIST_PATH + "/{id}/" + "delete", method = GET)
	public String deleteCustomer(@PathVariable Long id, final RedirectAttributes model) {
		customerService.setCustomerNotVisible(id);
		
		model.addFlashAttribute("message", true);
		
		return "redirect:" + CUSTOMERS_LIST_PATH + "/1";
	}
	
	/* Edit customer */
	
	@RequestMapping(value = CUSTOMERS_LIST_PATH + "/{id}/edit", method = GET)
	public String showEditCustomerPage(@PathVariable Long id, final Model model) {
		model.addAttribute(EDITED_CUSTOMER_ATTRIBUTE, customerRepository.findOne(id));
		return EDIT_CUSTOMER_VIEW;
	}
	
	@RequestMapping(value = CUSTOMERS_LIST_PATH + "/{id}/edit", method = POST)
	public String processEditCustomerPage(@PathVariable Long id, @Valid @ModelAttribute(NEW_CUSTOMER_FORM_ATTRIBUTE) NewCustomerForm form, 
			final RedirectAttributes ra, final BindingResult results) {
		if(results.hasErrors()) {
			return EDIT_CUSTOMER_VIEW;
		}
		customerService.editCustomer(id, form);
		return "redirect:" + CUSTOMERS_LIST_PATH + "/1";
	}
	
	/* Customers list in JSon */
	
	@ResponseBody
	@RequestMapping(value = GET_CUSTOMERS_JSON, method = POST)
	public List<Customer> getCustomersJson(@RequestBody String pattern) {
		return customerRepository.findAll(hasOwnerAndFiltered(userContext.getSignedUser(), pattern));
	}
	
	@ResponseBody
	@RequestMapping(value = GET_PROPER_CUSTOMER_JSON, method = POST)
	public Customer getCustomerJson(@RequestBody Long id) {
		return customerRepository.findOne(id);
	}
 }
