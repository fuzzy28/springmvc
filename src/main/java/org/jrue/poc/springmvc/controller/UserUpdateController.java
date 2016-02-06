package org.jrue.poc.springmvc.controller;

import org.jrue.poc.springmybatis.domain.User;
import org.jrue.poc.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="update/{userId}")
public class UserUpdateController {

	private UserService userService;
	
	@Autowired
	public UserUpdateController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showUpdateScreen(@PathVariable String userId, Model model) {
		User user = userService.findOne(Long.parseLong(userId));
		model.addAttribute("user", user);
		return "registration/update";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String updateUser(@PathVariable String userId,User user) {
		user.setId(Integer.parseInt(userId));
		userService.update(user);
		return "redirect:/home";
	}
}
