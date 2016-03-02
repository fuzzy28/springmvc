package org.jrue.poc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorHandlerController {
	
	@RequestMapping("/403")
	public String accessDeniedPage(Model model) {
		model.addAttribute("message", "you don't have permission to access this page!");
		return "errors/genericerror";
	}
	
	@RequestMapping("/404")
	public String pageNotFound(Model model) {
		model.addAttribute("message", " sorry we can't find the page you are looking for!");
		return "errors/genericerror";
	}
	
}
