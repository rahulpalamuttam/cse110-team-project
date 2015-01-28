package com.googleit.telecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.googleit.telecom.dao.AuthDAO;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthDAO authDAO;

    @RequestMapping("/login")
    public String login() {
        return "auth/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "auth/logout";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String registerForm(@RequestParam("email") String email, @RequestParam("password") String password) {
        
        authDAO.authenticate(email, password);
        return "auth/login";
    }
}
