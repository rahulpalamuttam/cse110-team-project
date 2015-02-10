package com.googleit.telecom.controllers;


import com.googleit.telecom.dao.ServiceDAO;
import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.models.items.Service;
import com.googleit.telecom.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ServiceDAO serviceDAO;
    @RequestMapping(value={"/","","/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User dude = userDAO.getUser(email);
        List<Service> subscribedServices = serviceDAO.getSubscribedService(dude.getId());
        List<Service> unsubscribedServices = serviceDAO.getUnsubscribedService(dude.getId());
        model.addAttribute("subscribedServices", subscribedServices);
        model.addAttribute("unsubscribedServices", unsubscribedServices);
        model.addAttribute("user", dude.getEmail());

        // TODO :: We need to take the data in dude and add it to home
        return "dashboard/home";
    }

    @RequestMapping(value="/services")
    public String service() {
        return "dashboard/services";
    }
}
