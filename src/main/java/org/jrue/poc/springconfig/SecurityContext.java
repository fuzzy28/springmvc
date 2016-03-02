package org.jrue.poc.springconfig;

import org.jrue.poc.springmybatis.service.UserService;
import org.jrue.poc.springmybatis.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebMvcSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public RememberMeServices rememberMeServices() {
		TokenBasedRememberMeServices rememberMe =
				new TokenBasedRememberMeServices("springmvckey", userService());
		rememberMe.setParameter("rememberMe");
		return rememberMe;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {	
		auth
			.userDetailsService(userService())
			.passwordEncoder(passwordEncoder());
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
				.permitAll()
			.and()
				.rememberMe()
				.rememberMeServices(rememberMeServices())
			.and()
				.sessionManagement()
				.invalidSessionUrl("/signin?sessionExpired")
				.maximumSessions(1)
				.expiredUrl("/signin?sessionExpired");
	}
	
}