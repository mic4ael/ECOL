package pl.indecoders.archetype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import pl.indecoders.archetype.service.account.UserService;

@Configuration
@ImportResource(value = "classpath:spring-security-context.xml")
public class SecurityConfig {
	
	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("remember-me-key", userService());
	}
	
	@Bean
	public ShaPasswordEncoder passwordEncoder() {
		return new ShaPasswordEncoder(256);
	}
}