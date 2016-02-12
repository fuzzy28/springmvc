package org.jrue.poc.springmvc.controller;

import org.jrue.poc.springmybatis.domain.User;
import org.jrue.poc.springmybatis.service.RoleService;
import org.jrue.poc.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

	private UserService userService;
	private RoleService roleService;
	
	@Autowired
	public UserRegistrationController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "new")
	public String displayRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.findAllRoles());
		return "registration/register";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String registerNewUser(User user) {
		userService.save(user);
		return "redirect:/home";
	}
}
