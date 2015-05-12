package pl.indecoders.archetype.config;

import java.security.Principal;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
	"pl.indecoders.archetype"
})
public class TestWebAppContext {
	
	@Bean
	public Principal getPrincipal() {
		Principal principal = Mockito.mock(Principal.class);
		Mockito.when(principal.getName()).thenReturn("crossner90@gmail.com");
		return principal;
	}
}