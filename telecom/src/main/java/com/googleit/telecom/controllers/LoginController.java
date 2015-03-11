package com.googleit.telecom.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("user") != null) {
            logger.info("Already in session redirect to home");
            return "redirect:/";
        }

        logger.info("login method");
        return "auth/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        logger.info("logout method");
        return "auth/logout";
    }
}