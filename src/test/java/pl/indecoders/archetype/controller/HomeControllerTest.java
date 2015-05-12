package pl.indecoders.archetype.controller;

import java.security.Principal;

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
import pl.indecoders.archetype.form.account.PersonalInformationForm;
import pl.indecoders.archetype.navigation.Navigator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestWebAppContext.class})
public class HomeControllerTest {
	
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webAppCtx;
	
	@Autowired
	Principal principal;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webAppCtx)
				.build();
	}
	
	@Test
	public void showHomePageWithoutPrincipalTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(Navigator.HOME_PATH))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andExpect(MockMvcResultMatchers.view().name(Navigator.LOGIN_VIEW));
	}
	
	@Test
	public void showHomePageWhenLoggedInTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(Navigator.HOME_PATH).principal(principal))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andExpect(MockMvcResultMatchers.model().attribute(Navigator.CURRENTLY_SIGNED, Matchers.notNullValue()))
			   .andExpect(MockMvcResultMatchers.view().name(Navigator.HOME_VIEW));
	}
	
	@Test
	public void updatePersonalInfoWithBlankFormTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(Navigator.HOME_PATH)
				.sessionAttr(Navigator.PERSONAL_INFORMATIONS_FORM, new PersonalInformationForm())
				.principal(principal)
		)
		.andExpect(MockMvcResultMatchers.status().is(302))
		.andExpect(MockMvcResultMatchers.redirectedUrl(Navigator.HOME_PATH))
		.andExpect(MockMvcResultMatchers.model().attributeHasNoErrors(Navigator.PERSONAL_INFORMATIONS_FORM))
		.andExpect(MockMvcResultMatchers.view().name(Navigator.MAIN_REDIRECT));
	}
	
	@Test
	public void updatePersonalInfoWithNotValidFormTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(Navigator.HOME_PATH)
				.param("nip", "test")
				.param("email", "test")
				.sessionAttr(Navigator.PERSONAL_INFORMATIONS_FORM, new PersonalInformationForm())
				.principal(principal)
		)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors(Navigator.PERSONAL_INFORMATIONS_FORM, "nip", "email"))
		.andExpect(MockMvcResultMatchers.view().name(Navigator.HOME_VIEW));
	}
	
	@Test
	public void updatePersonalInfoWithValidFormTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(Navigator.HOME_PATH)
				.param("nip", "999-123-09-09")
				.param("email", "crossner90@gmail.com")
				.sessionAttr(Navigator.PERSONAL_INFORMATIONS_FORM, new PersonalInformationForm())
				.principal(principal)
		)
		.andExpect(MockMvcResultMatchers.status().is(302))
		.andExpect(MockMvcResultMatchers.redirectedUrl(Navigator.HOME_PATH))
		.andExpect(MockMvcResultMatchers.model().attributeHasNoErrors(Navigator.PERSONAL_INFORMATIONS_FORM))
		.andExpect(MockMvcResultMatchers.model().attribute(Navigator.CURRENTLY_SIGNED, Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.model().attribute(Navigator.PERSONAL_INFORMATIONS_FORM, Matchers.hasProperty("email", Matchers.is("crossner90@gmail.com"))))
		.andExpect(MockMvcResultMatchers.view().name(Navigator.MAIN_REDIRECT));
	}
}