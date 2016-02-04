package org.jrue.poc.springmvc.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrue.poc.springmybatis.domain.User;
import org.jrue.poc.springmybatis.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
public class HomeControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<User> users = Arrays.asList(new User(), new User(), new User());
		UserService userService = Mockito.mock(UserService.class);
		
		Mockito.when(userService.findAll())
			   .thenReturn(users);
		
		HomeController controller = new HomeController(userService);
		
		Map<String,Object> model = new HashMap<String,Object>();
		String view = controller.showHomePage(model);
		
		Assert.assertEquals("jsp/home", view);
		
		Assert.assertSame(users, model.get("users"));
		Mockito.verify(userService).findAll();
	}

}