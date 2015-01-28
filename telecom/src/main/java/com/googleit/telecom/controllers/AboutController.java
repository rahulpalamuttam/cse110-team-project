
package com.googleit.telecom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/about")
	public String login(Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
		String data ="This is Data from databases";
		
		model.addAttribute("var", data);
		return "pages/about";
	}
}
