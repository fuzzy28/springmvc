package org.jrue.poc.springconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {	
		//if using in memory authentication
		auth.inMemoryAuthentication()
			.withUser("JOEL").password("12345").roles("ADMIN","USER").and()
			.withUser("USER").password("12345").roles("USER");
		//if using database authentication
		
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/register/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/update/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/**").access("hasRole('ROLE_USER')")
			.and()
				.formLogin()
				.loginPage("/signin")
				.defaultSuccessUrl("/home", true)
				.failureUrl("/signin?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()				
			.and()
				.logout()
				.logoutUrl("/logout")
				.permitAll();
	}
}