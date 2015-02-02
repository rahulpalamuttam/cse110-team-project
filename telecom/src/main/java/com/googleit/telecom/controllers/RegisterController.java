package com.googleit.telecom.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.models.User;

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

    @Autowired
    private UserDAO userDAO;

    /**
     * Shows the registeration form
     * 
     * @param User
     * @return string of regiter.html path
     */
    @RequestMapping("/register")
    public String registerForm(User user) {
        return "register/register";
    }

    /**
     * Upon POST request register user
     * when form validation pass.
     * 
     * @param User
     * @param BindingResult
     * @return redirect to dashboard;
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult bindingResult) {

        // Invalid form -> show register form page
        if (bindingResult.hasErrors()) return "register/register";
        
        System.out.println(user.getEmail() + " / " + user.getPassword() + " / " + user.getFirst_name() + " / " + user.getId());
        
        /* TODO :: Password Confrim validation using javascript */

        userDAO.insert(user);

        /* TODO :: redirect to DASHBOARD when dashboard is ready */
        return "register/register_success";
    }
}