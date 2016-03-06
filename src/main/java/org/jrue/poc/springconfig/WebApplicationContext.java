package org.jrue.poc.springconfig;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.jrue.poc.aspect.EmailSendingAspect;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("org.jrue.poc")
@Import({ SecurityContext.class, EmailSendingAspect.class, EmailContext.class })
@EnableAspectJAutoProxy
public class WebApplicationContext {

	/**
	 * Constant Variables
	 */
	private static final String VIEWS = "/WEB-INF/views/th/";
	private static final String EXT_TH = ".html";

	/**
	 * View Resolver for Thymeleaf pages.
	 */
	@Bean
	public ITemplateResolver templateResolver() {
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix(VIEWS);
		templateResolver.setSuffix(EXT_TH);
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(templateResolver());
		templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver thViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		return viewResolver;
	}

	@Bean
	public static PropertyOverrideConfigurer overrideConfigurer() {
		PropertyOverrideConfigurer configurer = new PropertyOverrideConfigurer();
		configurer.setLocation(new ClassPathResource("mailconfig.properties"));
		configurer.setIgnoreResourceNotFound(true);
		return configurer;
	}

	@Bean
	public VelocityEngine velocityEngine() {
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		VelocityEngine velocityEngine = new VelocityEngine(props);
		return velocityEngine;
	}
}