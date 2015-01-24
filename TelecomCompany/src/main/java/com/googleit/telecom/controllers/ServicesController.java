
package com.googleit.telecom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServicesController {
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/services")
	public String login(Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
		return "pages/services";
	}
}
