package pl.indecoders.archetype.controller.account;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.indecoders.archetype.config.TestWebAppContext;
import pl.indecoders.archetype.form.account.RegisterAccountForm;
import pl.indecoders.archetype.navigation.Navigator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestWebAppContext.class})
public class RegistrationControllerTest {
	
	MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext ctx;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void showRegistrationPageTest() throws Exception {
		mockMvc
			.perform(MockMvcRequestBuilders.get(Navigator.REGISTRATION_PATH))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attribute(Navigator.REGISTER_ACCOUNT_FORM_ATTRIBUTE, Matchers.notNullValue()))
			.andExpect(MockMvcResultMatchers.view().name(Navigator.REGISTRATION_VIEW));
	}
	
	@Test
	public void tryToRegisterWithDifferentPasswordsTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(Navigator.REGISTRATION_PATH)
				.param("email", "cross@gmail.com")
				.param("password", "pass1")
				.param("repeatedPassword", "pass2")
				.sessionAttr(Navigator.REGISTER_ACCOUNT_FORM_ATTRIBUTE, new RegisterAccountForm())
		)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.model().attributeHasErrors(Navigator.REGISTER_ACCOUNT_FORM_ATTRIBUTE, "registerAccount"))
		.andExpect(MockMvcResultMatchers.view().name(Navigator.REGISTRATION_VIEW));
	}
	
	@Test
	public void tryToRegisterWithNonUniqueEmailTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(Navigator.REGISTRATION_PATH)
				.param("email", "crossner90@gmail.com")
				.param("password", "pass2")
				.param("repeatedPassword", "pass2")
				.sessionAttr(Navigator.REGISTER_ACCOUNT_FORM_ATTRIBUTE, new RegisterAccountForm())
		)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors(Navigator.REGISTER_ACCOUNT_FORM_ATTRIBUTE, "email"))
		.andExpect(MockMvcResultMatchers.view().name(Navigator.REGISTRATION_VIEW));
	}

}