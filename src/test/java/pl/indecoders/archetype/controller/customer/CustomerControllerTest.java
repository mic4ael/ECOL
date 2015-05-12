package pl.indecoders.archetype.controller.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pl.indecoders.archetype.config.TestWebAppContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestWebAppContext.class})
public class CustomerControllerTest {
	
	@Test
	public void indexPage() {
		"a".contains("a");
	}
}