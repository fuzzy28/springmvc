package org.jrue.poc.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;


@Configuration
@EnableWebMvc
@ComponentScan("org.jrue.poc.springmvc.controller")
@Import(SecurityContext.class)
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
}