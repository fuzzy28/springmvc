package org.jrue.poc.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.jrue.poc.springmybatis.domain.Role;
import org.jrue.poc.springmybatis.service.RoleService;
import org.jrue.poc.springmybatis.service.UserService;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

public class RegistrationControllerTest {

	@Test
	public void whenUserClickNewUserThenShowRegistrationForm() {
		UserService userService = Mockito.mock(UserService.class);
		RoleService roleService = Mockito.mock(RoleService.class);

		Model model = new ExtendedModelMap();

		UserRegistrationController controller = new UserRegistrationController(
				userService, roleService);

		assertEquals("registration/register",
				controller.displayRegistrationForm(model));
	}

	@Test
	public void whenFormDisplayedThenListOfRolesWillBeAvailable() {
		UserService userService = Mockito.mock(UserService.class);
		RoleService roleService = Mockito.mock(RoleService.class);

		Role admin = new Role();
		admin.setRoleId(1);
		admin.setRoleName("ADMIN");

		Role user = new Role();
		user.setRoleId(1);
		user.setRoleName("USER");

		List<Role> roles = new ArrayList<Role>();
		roles.add(admin);

		Mockito.when(roleService.findAllRoles()).thenReturn(roles);

		UserRegistrationController userController = new UserRegistrationController(
				userService, roleService);
		
		Model model = new ExtendedModelMap();
		userController.displayRegistrationForm(model);
		
		assertSame(model.asMap().get("roles"), roles);
		Mockito.verify(roleService).findAllRoles();
	}

}