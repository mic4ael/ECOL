package pl.indecoders.archetype.controller.invoice;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;
import static pl.indecoders.archetype.navigation.Navigator.INVOICES_LIST_PATH;
import static pl.indecoders.archetype.navigation.Navigator.INVOICES_LIST_VIEW;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * The Class InvoiceController.
 * @author Mateusz
 */
@Controller
@SessionAttributes(CURRENTLY_SIGNED)
public class InvoiceController {

	@RequestMapping(value = INVOICES_LIST_PATH, method = GET)
	public String showInvoicesListPage(final HttpSession session) {
		return INVOICES_LIST_VIEW;
	}
}
