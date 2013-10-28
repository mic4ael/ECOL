package pl.indecoders.archetype.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;
import static pl.indecoders.archetype.navigation.Navigator.HOME_PATH;
import static pl.indecoders.archetype.navigation.Navigator.HOME_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.LOGIN_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.MAIN_REDIRECT;
import static pl.indecoders.archetype.navigation.Navigator.PERSONAL_INFORMATIONS_FORM;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.indecoders.archetype.form.account.PersonalInformationForm;
import pl.indecoders.archetype.security.SecurityUserContext;
import pl.indecoders.archetype.service.account.ProfileService;

/**
 * The Class HomeController
 * @author Mateusz
 */
@Controller
public class HomeController {

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private SecurityUserContext userContext;
	
	@RequestMapping(value = HOME_PATH, method = GET)
	public String index(final Principal principal, final Model model, final HttpSession session) {
		if(principal != null) {
			model.addAttribute(PERSONAL_INFORMATIONS_FORM, profileService.preparePersonalInformationsForm(userContext.getSignedUser(principal)));
			model.addAttribute(CURRENTLY_SIGNED, userContext.getSignedUser(principal));
			return HOME_VIEW;
		}
		return LOGIN_VIEW;
	}
	
	@RequestMapping(value = HOME_PATH, method = POST)
	public String updatePersonalInformations(@Valid @ModelAttribute(PERSONAL_INFORMATIONS_FORM) PersonalInformationForm form, final Model model, final Principal principal) {
		profileService.processPersonalInformationsForm(form, userContext.getSignedUser(principal));
		model.addAttribute(PERSONAL_INFORMATIONS_FORM, profileService.preparePersonalInformationsForm(userContext.getSignedUser(principal)));
		model.addAttribute(CURRENTLY_SIGNED, userContext.getSignedUser(principal));
		return MAIN_REDIRECT;
	}
}
