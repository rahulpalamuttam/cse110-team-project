package com.googleit.telecom.controllers;


import com.googleit.telecom.dao.CustomerDAO;
import com.googleit.telecom.dao.ServiceDAO;
import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.models.items.Service;
import com.googleit.telecom.models.users.Customer;
import com.googleit.telecom.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ServiceDAO serviceDAO;
    @Autowired
    private CustomerDAO customerDAO;

    /**
     * Fetches the authenticated user
     */
    private User getAuthenticated(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User dude = userDAO.getUser(email);
        return dude;
    }

    @RequestMapping(value={"/","","/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        User currentAuthenticated = getAuthenticated();
        model.addAttribute("user", currentAuthenticated.getEmail());
        return "dashboard/home";
    }

    @RequestMapping(value={"/services"}, method = RequestMethod.POST)
    public String updateSubscription(@RequestParam(value = "subscribe", required = false) String[] subscribe,
                        @RequestParam(value = "cancel",    required = false) String[] cancel) {


        User currentAuthenticated = getAuthenticated();
        long user_id = currentAuthenticated.getId();

        if(subscribe != null && subscribe.length >0)
            for(String service_id : subscribe)
                serviceDAO.addService(Long.valueOf(service_id), user_id);

        if(cancel != null && cancel.length>0)
            for(String service_id : cancel)
                serviceDAO.unsubscribeService(Long.valueOf(service_id), user_id);

        return "redirect:/dashboard/services";
    }

    @RequestMapping(value="/services")
    public String service(Model model) {
        User dude = getAuthenticated();
        long user_id = dude.getId();
        List<Service> subscribedServices = serviceDAO.getSubscribedService(dude.getId());
        List<Service> unsubscribedServices = serviceDAO.getUnsubscribedService(dude.getId());
        model.addAttribute("subscribedServices", subscribedServices);
        model.addAttribute("unsubscribedServices", unsubscribedServices);
        model.addAttribute("user", dude.getEmail());
        return "dashboard/services";
    }

    @RequestMapping(value={"/customerServices"}, method = RequestMethod.POST)
    public String updateCustomerSubscription(@RequestParam(value = "email", required = false) String email, Model model) {
        System.out.println(email);
        User dude = userDAO.getUser(email);
        long user_id = dude.getId();
        List<Service> subscribedServices = serviceDAO.getSubscribedService(dude.getId());
        List<Service> unsubscribedServices = serviceDAO.getUnsubscribedService(dude.getId());
        model.addAttribute("subscribedServices", subscribedServices);
        model.addAttribute("unsubscribedServices", unsubscribedServices);
        model.addAttribute("user", dude.getEmail());
        return "dashboard/services";
    }


    @RequestMapping("/customers")
    public String customer(Model model) {
        User dude = getAuthenticated();
        long user_id = dude.getId();

        List<Customer> myCustomers = customerDAO.getCustomers(user_id, userDAO);
        model.addAttribute("myCustomers", myCustomers);
        return "dashboard/customers";
    }
}