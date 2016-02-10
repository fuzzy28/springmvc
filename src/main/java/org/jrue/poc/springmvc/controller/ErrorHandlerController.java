package org.jrue.poc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorHandlerController {
	
	@RequestMapping("/403")
	public String accessDeniedPage() {
		return "errors/accessdenied";
	}
	
	@RequestMapping("/404")
	public String pageNotFound() {
		return "errors/pagenotfound";
	}
	
}
