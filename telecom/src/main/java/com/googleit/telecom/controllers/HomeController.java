package com.googleit.telecom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController serves the homepage view.
 */

@Controller
public class HomeController {
	
	@RequestMapping(value = {"/", "/home"})
	public String home() {
		return "home";
	}
}
