package pl.indecoders.archetype.controller.invoice;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_PATH;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_VIEW;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * The Class NewInvoiceController.
 * @author Mateusz
 */
@Controller
@SessionAttributes(CURRENTLY_SIGNED)
@RequestMapping(value = NEW_INVOICE_PATH)
public class NewInvoiceController {

	@RequestMapping(value = "", method = GET)
	public String showNewInvoicePage(final HttpSession session) {
		return NEW_INVOICE_VIEW;
	}
}
