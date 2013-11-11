package pl.indecoders.archetype.controller.invoice;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;
import static pl.indecoders.archetype.navigation.Navigator.INVOICES_LIST_PAGES_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.INVOICES_LIST_PATH;
import static pl.indecoders.archetype.navigation.Navigator.INVOICES_LIST_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.INVOICE_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.INVOICE_DETAILS_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.INVOICE_DETAILS_PATH;
import static pl.indecoders.archetype.navigation.Navigator.INVOICE_DETAILS_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.INVOICE_LIST_DIR_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.INVOICE_LIST_SORT_ATTRIBUTE;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.indecoders.archetype.repository.invoice.InvoiceRepository;
import pl.indecoders.archetype.security.SecurityUserContext;
import pl.indecoders.archetype.service.invoice.InvoiceService;
import pl.indecoders.archetype.utils.PaginationUtils;

/**
 * The Class InvoiceController.
 * 
 * @author Mateusz
 */
@Controller
@SessionAttributes(CURRENTLY_SIGNED)
public class InvoiceController {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private SecurityUserContext userContext;

	public static final Integer RESULTS_ON_PAGE = 10;

	@ModelAttribute(INVOICES_LIST_PAGES_ATTRIBUTE)
    public Integer amountOfPages() {
            return new PaginationUtils(RESULTS_ON_PAGE).numberOfPages(invoiceRepository.countByOwner(userContext.getSignedUser()));
    }

	/* List of invoices */
	
	@RequestMapping(value = INVOICES_LIST_PATH + "/{page}", method = GET)
	public String showInvoicesListPage(@PathVariable final Integer page, final Model model, final HttpSession session) {
		if (session.getAttribute(INVOICE_LIST_SORT_ATTRIBUTE) == null) {
			model.addAttribute(INVOICE_ATTRIBUTE, invoiceService.getPagedInvoices(userContext.getSignedUser(), page - 1, RESULTS_ON_PAGE));
			return INVOICES_LIST_VIEW;
		}
		model.addAttribute(
				INVOICE_ATTRIBUTE,
				invoiceService.getPagedInvoices(userContext.getSignedUser(), page - 1, RESULTS_ON_PAGE,
						(String) session.getAttribute(INVOICE_LIST_SORT_ATTRIBUTE), (String) session.getAttribute(INVOICE_LIST_DIR_ATTRIBUTE)));
		return INVOICES_LIST_VIEW;
	}

	@RequestMapping(value = INVOICES_LIST_PATH + "/{page}", method = POST)
	public String sortInvoicesListPage(final HttpSession session, final Model model, @PathVariable Integer page, @RequestParam("dir") String direction,
			@RequestParam("property") String property) {
		session.setAttribute(INVOICE_LIST_SORT_ATTRIBUTE, property);
		session.setAttribute(INVOICE_LIST_DIR_ATTRIBUTE, direction);
		model.addAttribute(INVOICE_ATTRIBUTE, invoiceService.getPagedInvoices(userContext.getSignedUser(), page - 1, RESULTS_ON_PAGE, property, direction));
		return INVOICES_LIST_VIEW;
	}

	/* Invoice details */
	
	@RequestMapping(value = INVOICE_DETAILS_PATH + "/{invoiceId}", method = GET)
	public String showInvoiceDetailsPage(@PathVariable final Long invoiceId, final Model model) {
		model.addAttribute(INVOICE_DETAILS_ATTRIBUTE, invoiceRepository.findOne(invoiceId));
		return INVOICE_DETAILS_VIEW;
	}
	
	/* PDF generation */
}
