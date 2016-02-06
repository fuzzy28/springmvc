package org.jrue.poc.springmvc.controller;

import java.util.Arrays;
import java.util.List;
import org.jrue.poc.springmybatis.domain.User;
import org.jrue.poc.springmybatis.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
public class HomeControllerTest {

	@Test
	public void whenUserAccessHomePageThenShowListOfUsers() {
		List<User> users = Arrays.asList(new User(), new User(), new User());
		UserService userService = Mockito.mock(UserService.class);
		
		Mockito.when(userService.findAll())
			   .thenReturn(users);
		
		HomeController controller = new HomeController(userService);
		
		Model model = new ExtendedModelMap();
		String view = controller.showHomePage(model);
		
		Assert.assertEquals("homepage/home", view);
		
		Assert.assertSame(users, model.asMap().get("users"));
		Mockito.verify(userService).findAll();
	}

}