package pl.indecoders.archetype.controller.account;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static pl.indecoders.archetype.navigation.Navigator.BAD_CREDENTIALS;
import static pl.indecoders.archetype.navigation.Navigator.BAD_CREDENTIALS_KEY;
import static pl.indecoders.archetype.navigation.Navigator.LOGIN_PATH;
import static pl.indecoders.archetype.navigation.Navigator.LOGIN_VIEW;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class LoginController.
 * @author Mateusz
 */
@Controller
public class LoginController {

	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = LOGIN_PATH, method = GET)
	public String showLoginPage() {
		return LOGIN_VIEW;
	}
	
	@RequestMapping(value = LOGIN_PATH + "/" + BAD_CREDENTIALS, method = GET)
	public String showBadCredentialsAlert(final Model model, final Locale locale) {
		model.addAttribute(BAD_CREDENTIALS_KEY, messageSource.getMessage(BAD_CREDENTIALS_KEY, null, locale));
		return LOGIN_VIEW;
	}
}
