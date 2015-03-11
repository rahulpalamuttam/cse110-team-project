package com.googleit.telecom.controllers;

import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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