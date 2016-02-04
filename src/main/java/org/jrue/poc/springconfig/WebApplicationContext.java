package org.jrue.poc.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;


@Configuration
@EnableWebMvc
@ComponentScan("org.jrue.poc.springmvc.controller")
public class WebApplicationContext extends WebMvcConfigurerAdapter {
	
	/**
	 * Constant Variables
	 */
	private static final String VIEWS = "/WEB-INF/views/";
	private static final String EXT_JSP = ".jsp";
	private static final String EXT_TH = ".html";
	private static final String VIEW_NAME_JSP = "jsp/*";
	private static final String VIEW_NAME_TH = "th/*";
	
	/**
	 * View Resolver for Java Server Pages.
	 */
	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix(VIEWS);
		viewResolver.setSuffix(EXT_JSP);
		viewResolver.setViewNames(VIEW_NAME_JSP);
		return viewResolver;
	}
	
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
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver thViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setViewNames(new String[] {VIEW_NAME_TH});
		return viewResolver;
	}
	
}