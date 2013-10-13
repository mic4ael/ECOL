package pl.indecoders.archetype.controller.customer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;
import static pl.indecoders.archetype.navigation.Navigator.CUSTOMERS_LIST_PATH;
import static pl.indecoders.archetype.navigation.Navigator.CUSTOMERS_LIST_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.NEW_CUSTOMER_PATH;
import static pl.indecoders.archetype.navigation.Navigator.NEW_CUSTOMER_VIEW;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * The Class CustomerController.
 * @author Mateusz
 */
@Controller
@SessionAttributes(CURRENTLY_SIGNED)
public class CustomerController {

	@RequestMapping(value = NEW_CUSTOMER_PATH, method = GET)
	public String showNewCustomerPage() {
		return NEW_CUSTOMER_VIEW;
	}
	
	@RequestMapping(value = CUSTOMERS_LIST_PATH, method = GET)
	public String showCustomersListPage() {
		return CUSTOMERS_LIST_VIEW;
	}
}
