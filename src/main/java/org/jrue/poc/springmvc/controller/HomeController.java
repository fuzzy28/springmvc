package org.jrue.poc.springmvc.controller;

import org.jrue.poc.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private UserService userService;
	
	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
	public String showHomePage(Model model) {
		model.addAttribute("users", userService.findAll());
		return "homepage/home";
	}
}
