package org.jrue.poc.springmvc.controller;

import org.jrue.poc.springmybatis.domain.User;
import org.jrue.poc.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/delete/{userId}")
public class UserDeletionController {

	private UserService userService;
	
	@Autowired
	public UserDeletionController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String deleteSelectedUser(@PathVariable String userId) {
		User user = userService.findOne(Long.parseLong(userId));
		userService.delete(user);
		return "redirect:/home";
	}
	
}
