package com.googleit.telecom.controllers;

import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDAO userDAO;

    @Autowired
    AuthenticationManager authenticationManager;

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
    public String register(@Valid User user, BindingResult bindingResult, HttpServletRequest request, Model model) {
        boolean duplicate = false;
        String email = user.getEmail();
        String password = user.getPassword();

        if ( duplicate = userDAO.isDuplicate(user.getEmail()) ) {
            model.addAttribute("duplicate", "Email already exists.");
        }

        // Invalid form -> show register form page
        if (bindingResult.hasErrors() || duplicate) return "register/register";

        /* TODO :: Check for SQL error exception */
        userDAO.insert(user);

        autoLogin(email, password, request);

        return "register/register_success";
    }

    public void autoLogin(String email, String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
