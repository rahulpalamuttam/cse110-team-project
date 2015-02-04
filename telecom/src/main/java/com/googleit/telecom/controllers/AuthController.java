package com.googleit.telecom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login")
    public String login() {
        return "auth/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "auth/logout";
    }
}