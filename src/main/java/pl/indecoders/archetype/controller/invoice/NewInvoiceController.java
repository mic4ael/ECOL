package pl.indecoders.archetype.controller.invoice;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_ATTRIBUTES;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_FORM;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_NUMBER_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_PATH;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_PRODUCTS_PATH;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_PRODUCTS_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCT_ROW_FORM_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.SESSION_STORED_INVOICE;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.indecoders.archetype.form.account.PersonalInformationForm;
import pl.indecoders.archetype.form.invoice.InvoiceForm;
import pl.indecoders.archetype.form.invoice.ProductRowForm;
import pl.indecoders.archetype.repository.invoice.InvoiceRepository;
import pl.indecoders.archetype.security.SecurityUserContext;
import pl.indecoders.archetype.service.account.ProfileService;

/**
 * The Class NewInvoiceController.
 * @author Mateusz
 */
@Controller
@SessionAttributes(CURRENTLY_SIGNED)
@RequestMapping(value = NEW_INVOICE_PATH)
public class NewInvoiceController {

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private SecurityUserContext userContext;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@ModelAttribute(NEW_INVOICE_ATTRIBUTES)
	public PersonalInformationForm sendForm() {
		return profileService.preparePersonalInformationsForm(userContext.getSignedUser());
	}
	
	@ModelAttribute(NEW_INVOICE_NUMBER_ATTRIBUTE)
	public Long sendInvoiceNumber() {
		return invoiceRepository.countByOwner(userContext.getSignedUser()) + 1;
	}
	
	@RequestMapping(value = "", method = GET)
	public String showNewInvoicePage(final HttpSession session, final Model model) {
		model.addAttribute(NEW_INVOICE_FORM, new InvoiceForm());
		return NEW_INVOICE_VIEW;
	}
	
	@RequestMapping(value = "", method = POST)
	public String processNewInvoicePage(@Valid @ModelAttribute(NEW_INVOICE_FORM) InvoiceForm form, final BindingResult results, final HttpSession session) {
		if(results.hasErrors()) {
			return NEW_INVOICE_VIEW;
		}
		session.setAttribute(SESSION_STORED_INVOICE, form);
		return "redirect:new-invoice" + NEW_INVOICE_PRODUCTS_PATH;
	}
	
	@RequestMapping(value = NEW_INVOICE_PRODUCTS_PATH, method = GET)
	public String showNewInvoiceProductsPage(final Model model) {
		model.addAttribute(PRODUCT_ROW_FORM_ATTRIBUTE, new ProductRowForm());
		return NEW_INVOICE_PRODUCTS_VIEW;
	}
}
