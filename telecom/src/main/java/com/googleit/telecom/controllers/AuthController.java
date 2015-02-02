package com.googleit.telecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.googleit.telecom.dao.AuthDAO;

@Controller
@RequestMapping("/auth")
public class AuthController {
    static int trial = 0;

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

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginCheck(/*@RequestParam("email") String email, @RequestParam("password") String password, Model model*/) {
//        if(trial > 2) {
//            model.addAttribute("login_message", "You have more than 3 attempts.");
//            return "home";
//        }
//        if(authDAO.authenticate(email, password)) {
//            model.addAttribute("login_message", "YOU HAVE SUCCESSFULY LOGGED IN");
//            return "home";
//        }

//        trial++;

        return "auth/login";
    }
}
