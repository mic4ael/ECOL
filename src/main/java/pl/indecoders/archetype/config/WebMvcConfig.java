package pl.indecoders.archetype.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import pl.indecoders.archetype.conversion.ProductGroupConverter;
import pl.indecoders.archetype.conversion.ProductTypeConverter;
import pl.indecoders.archetype.conversion.ProductUnitConverter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	
	private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";
	private static final String TILES = "/WEB-INF/tiles/tiles.xml";
	private static final String VIEWS = "/WEB-INF/views/**/views.xml";
	
	private static final String RESOURCES_HANDLER = "/resources/";
	private static final String RESOURCES_LOCATION = RESOURCES_HANDLER + "**";
	
	private static final String MAIL_PROPS = "mail.properties";
	
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
		requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
		requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
		return requestMappingHandlerMapping;
	}
	
	@Bean
	public ConversionService conversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		Set<Converter> converters = new HashSet<Converter>();
		converters.add(new ProductGroupConverter());
		converters.add(new ProductTypeConverter());
		converters.add(new ProductUnitConverter());
		bean.setConverters(converters);
		return bean.getObject();
	}
	
	@Bean(name = "messageSource")
	public MessageSource configureMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MESSAGE_SOURCE);
		messageSource.setCacheSeconds(5);
		return messageSource;
	}
	
	@Bean(name="excelViewResolver")
    public ViewResolver getXmlViewResolver() {
        XmlViewResolver resolver = new XmlViewResolver();
        resolver.setLocation(new ServletContextResource(servletContext,
                    "/WEB-INF/views/pdf/pdf-views.xml"));
        resolver.setOrder(1);
        return resolver;
    }
	
	@Bean
	public TilesViewResolver configureTilesViewResolver() {
		return new TilesViewResolver();
	}
	
	@Bean
	public TilesConfigurer configureTilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {TILES, VIEWS});
		return configurer;
	}
	
	@Bean
	public Logger applicationLogger() {
		return LoggerFactory.getLogger("pl.indecoders.archetype");
	}
	
	@Bean
	JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Properties mailProps = new Properties();
		
		try {
			mailProps.load(this.getClass().getClassLoader().getResourceAsStream(MAIL_PROPS));
			mailSender.setJavaMailProperties(mailProps);
			mailSender.setUsername(mailProps.getProperty("mail.user"));
			mailSender.setPassword(mailProps.getProperty("mail.password"));
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}
		
		return mailSender;
	}
	
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(configureMessageSource());
		return validator;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new UserDetailsHandlerMethodArgumentResolver());
	}
	
	// custom argument resolver inner classes

	private static class UserDetailsHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

		public boolean supportsParameter(MethodParameter parameter) {
			return UserDetails.class.isAssignableFrom(parameter.getParameterType());
		}

		public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			Authentication auth = (Authentication) webRequest.getUserPrincipal();
			return auth != null && auth.getPrincipal() instanceof UserDetails ? auth.getPrincipal() : null;
		}
	}
}
