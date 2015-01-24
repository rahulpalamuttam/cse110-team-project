
package com.googleit.telecom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/login")
	public String login(Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("data","login");
		return "auth/login";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("data","logout");
		return "auth/logout";
	}
}
