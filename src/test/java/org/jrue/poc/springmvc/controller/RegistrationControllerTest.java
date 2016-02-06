package org.jrue.poc.springmvc.controller;

import org.jrue.poc.springmybatis.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

public class RegistrationControllerTest {

	
	@Test
	public void whenUserClickNewUserThenShowRegistrationForm() {
		UserService userService = Mockito.mock(UserService.class);
		
		Model model = new ExtendedModelMap();
		UserRegistrationController controller = new UserRegistrationController(userService);

		Assert.assertEquals("registration/register", controller.displayRegistrationForm(model));
	}
}
