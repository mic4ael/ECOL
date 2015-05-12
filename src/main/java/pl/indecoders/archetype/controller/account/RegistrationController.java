package pl.indecoders.archetype.controller.account;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.indecoders.archetype.navigation.Navigator.FINALIZE_REGISTRATION_KEY;
import static pl.indecoders.archetype.navigation.Navigator.MAIN_REDIRECT;
import static pl.indecoders.archetype.navigation.Navigator.REGISTER_ACCOUNT_FORM_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.REGISTRATION_PATH;
import static pl.indecoders.archetype.navigation.Navigator.REGISTRATION_VIEW;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.events.MailEventPublisher;
import pl.indecoders.archetype.form.account.RegisterAccountForm;
import pl.indecoders.archetype.repository.accout.AccountRepository;
import pl.indecoders.archetype.service.account.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MailEventPublisher mailEventPublisher;
	
	
	@RequestMapping(value = REGISTRATION_PATH, method = GET)
	public String showRegistrationPage(Model model) {
		model.addAttribute(REGISTER_ACCOUNT_FORM_ATTRIBUTE, new RegisterAccountForm());
		return REGISTRATION_VIEW;
	}

	@RequestMapping(value = REGISTRATION_PATH, method = POST)
	public String processRegistrationPage(@Valid 
										  @ModelAttribute(REGISTER_ACCOUNT_FORM_ATTRIBUTE) RegisterAccountForm form,
										  BindingResult result,
										  HttpServletRequest request) {
		
		if (result.hasErrors()) {
			return REGISTRATION_VIEW;
		}
		
		return containsFinalizeKey(request) ? finalizeRegistration(form) : MAIN_REDIRECT;
	}

	private boolean containsFinalizeKey(HttpServletRequest request) {
		if (request.getParameterMap().containsKey(FINALIZE_REGISTRATION_KEY)) {
			return true;
		}
		
		return false;
	}

	private String finalizeRegistration(RegisterAccountForm form) {
		Account registeredAccount = userService.registerNewAccount(form);
		userService.signin(registeredAccount);
		mailEventPublisher.publish(registeredAccount);
		
		return MAIN_REDIRECT;
	}
}
