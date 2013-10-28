package pl.indecoders.archetype.controller.invoice;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_ATTRIBUTES;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_NUMBER_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_PATH;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_VIEW;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.indecoders.archetype.form.account.PersonalInformationForm;
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
	public String showNewInvoicePage(final HttpSession session) {
		return NEW_INVOICE_VIEW;
	}
}
