package org.jrue.poc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/signin")
	public String showCustomLoginPage() {
		return "signin";
	}
	
}
