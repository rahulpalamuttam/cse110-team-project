package com.googleit.telecom.controllers;


import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value={"/","","/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();
        User dude = userDAO.get(email);

        model.addAttribute("user", dude.getPassword());

        // TODO :: We need to take the data in dude and add it to home
        return "dashboard/home";
    }

    @RequestMapping(value="/services")
    public String service() {
        return "dashboard/services";
    }
}
