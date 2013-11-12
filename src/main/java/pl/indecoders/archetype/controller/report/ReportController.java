package pl.indecoders.archetype.controller.report;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.indecoders.archetype.navigation.Navigator.REPORT_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.SOLD_REPORT_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.SOLD_REPORT_PATH;
import static pl.indecoders.archetype.navigation.Navigator.SOLD_REPORT_VIEW;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.indecoders.archetype.form.report.ReportForm;
import pl.indecoders.archetype.security.SecurityUserContext;
import pl.indecoders.archetype.service.invoice.InvoiceService;

/**
 * The Class ReportController.
 * @author Mateusz
 */
@Controller
public class ReportController {

	@Autowired
	private SecurityUserContext userContext;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@ModelAttribute(SOLD_REPORT_ATTRIBUTE)
	public ReportForm sendReportForm() {
		return new ReportForm();
	}
	
	@RequestMapping(value = SOLD_REPORT_PATH, method = GET)
	public String showSoldReportPage(final Model model) {
		return SOLD_REPORT_VIEW;
	}
	
	@RequestMapping(value = SOLD_REPORT_PATH, method = POST)
	public String processSoldReport(@Valid @ModelAttribute(SOLD_REPORT_ATTRIBUTE) final ReportForm form, final BindingResult result, final Model model) {
		//model.addAttribute(REPORT_ATTRIBUTE, invoiceService.prepareReportModel(form, userContext.getSignedUser()));
		return SOLD_REPORT_VIEW;
	}
}
