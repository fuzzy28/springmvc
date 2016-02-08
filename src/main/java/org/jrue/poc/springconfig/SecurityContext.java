package org.jrue.poc.springconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("JOEL").password("12345").roles("ADMIN","USER").and()
			.withUser("USER").password("12345").roles("USER");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/register/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/update/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/**").access("hasRole('ROLE_USER')")
			.and().formLogin().and().logout().permitAll();
	}
}
