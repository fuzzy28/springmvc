package org.jrue.poc.springmvc.controller;

import org.jrue.poc.springmybatis.domain.User;
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
	
	@Autowired
	public UserRegistrationController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "new")
	public String displayRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "registration/register";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String registerNewUser(User user) {
		userService.save(user);
		return "redirect:/home";
	}
}
