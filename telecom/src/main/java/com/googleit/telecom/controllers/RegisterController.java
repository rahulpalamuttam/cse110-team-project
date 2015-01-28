package com.googleit.telecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.models.User;

@Controller
public class RegisterController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "register/register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user, Model model) {
		// Testting...
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(date);
		
		user.setReg_date(currentTime);
		userDAO.insert(user);
		return "home";
	}
}