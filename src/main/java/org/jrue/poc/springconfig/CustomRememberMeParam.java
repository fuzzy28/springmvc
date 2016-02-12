package org.jrue.poc.springconfig;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

@Configuration
public class CustomRememberMeParam implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		if(bean instanceof AbstractRememberMeServices) {
			AbstractRememberMeServices rememberMe = (AbstractRememberMeServices) bean;
			rememberMe.setParameter("rememberMe");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		return bean;
	}

}
