package pl.indecoders.archetype.controller.account;

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
import pl.indecoders.archetype.navigation.Navigator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestWebAppContext.class})
public class LoginControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext ctx;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void showLoginHomePageTest() throws Exception {
		mockMvc
			.perform(MockMvcRequestBuilders.get(Navigator.LOGIN_PATH))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name(Navigator.LOGIN_VIEW));
	}
	
}