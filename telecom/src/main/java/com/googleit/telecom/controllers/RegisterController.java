package com.googleit.telecom.controllers;

import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 *
 * Class : RegisterContorller
 * Pakage : com.googleit.telecom.controllers
 *
 * The RegisterController class manages user registeration and insert
 * user's data where the form follows User model.
 *
 */

@Controller
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/register")
    public String registerForm(User user) {
        logger.info("registerForm method");
        return "register/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult bindingResult, HttpServletRequest request, Model model) {
        boolean duplicate = userDAO.isDuplicate(user);

        if (duplicate)
            model.addAttribute("duplicate", "Email already exists.");

        // Invalid form -> show register form page
        if (bindingResult.hasErrors() || duplicate)
            return "register/register";

        userDAO.register(user);


        return "register/register_success";
    }
}
