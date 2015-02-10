package com.googleit.telecom.controllers;


import com.googleit.telecom.dao.ServiceDAO;
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

    @RequestMapping(value={"", "/", "/home"})
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getName());
        return "dashboard/home";
    }

    @RequestMapping(value="/services")
    public String service() {
        return "dashboard/services";
    }
}
