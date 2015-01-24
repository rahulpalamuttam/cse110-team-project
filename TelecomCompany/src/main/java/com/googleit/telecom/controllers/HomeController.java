package com.googleit.telecom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String home(Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("data", "home");
		return "home";
	}
}
