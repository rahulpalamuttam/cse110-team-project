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

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(@RequestParam(value="error", required=false) String error, Model model) {
//        if(error!=null) model.addAttribute("error", "Email and Password don't match");

        return "auth/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "auth/logout";
    }

//    @RequestMapping(value="/login", method = RequestMethod.POST)
//    public String loginCheck(/*@RequestParam("email") String email, @RequestParam("password") String password, Model model*/) {
//
//        return "auth/login";
//    }
}
