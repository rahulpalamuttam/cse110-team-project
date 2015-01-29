package com.googleit.telecom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController serves the homepage view.
 */

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
}
