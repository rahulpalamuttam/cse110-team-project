
package com.googleit.telecom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServicesController {
	
	@RequestMapping("/services")
	public String login(Model model) {
		return "pages/services";
	}
}
