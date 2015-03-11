package com.googleit.telecom.controllers;


import com.googleit.telecom.dao.CustomerDAO;
import com.googleit.telecom.dao.ServiceDAO;
import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.dao.packageDAO;
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
    @Autowired
    private packageDAO packageDao;
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
        model.addAttribute("user", currentAuthenticated);
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

        if(cancel != null && cancel.length>0) {
            for (String service_id : cancel) {
                System.out.println(service_id);
                serviceDAO.unsubscribeService(Long.valueOf(service_id), user_id);
            }
        }
        return "redirect:/dashboard/services";
    }

    @RequestMapping(value="/services")
    public String service(Model model) {
        User dude = getAuthenticated();
        long user_id = dude.getId();
        Customer cust = customerDAO.getCustomer(user_id, serviceDAO, packageDao);
        model.addAttribute("CUSTOMER", cust);
        String type = "services";
        model.addAttribute("type", type);
        return "dashboard/services";
    }

    @RequestMapping(value={"/customerServices"}, method = RequestMethod.POST)
    public String updateCustomerSubscription(@RequestParam(value = "identification", required = false) String user_id, Model model) {
//        System.out.println(email);
//        User dude = userDAO.getUser(email);
//        long user_id = dude.getId();
        System.out.println("customer id : " + user_id );
        Customer cust = customerDAO.getCustomer(Long.parseLong(user_id), serviceDAO, packageDao);
        String type = "customerServicesUpdate";
        model.addAttribute("type", type);
        model.addAttribute("CUSTOMER", cust);
        return "dashboard/services";
    }

    @RequestMapping(value={"/customerPackages"}, method = RequestMethod.POST)
    public String updateCustomerPackageSubscription(@RequestParam(value = "identification", required = false) String user_id, Model model) {
//        System.out.println(email);
//        User dude = userDAO.getUser(email);
//        long user_id = dude.getId();
        System.out.println("customer id : " + user_id );
        Customer cust = customerDAO.getCustomer(Long.parseLong(user_id), serviceDAO, packageDao);
        String type = "customerPackagesUpdate";
        model.addAttribute("type", type);
        model.addAttribute("CUSTOMER", cust);
        return "dashboard/packages";
    }

    @RequestMapping(value={"/customerServicesUpdate"}, method = RequestMethod.POST)
    public String customerUpdateSubscription(@RequestParam(value = "subscribe", required = false) String[] subscribe,
                                     @RequestParam(value = "cancel",    required = false) String[] cancel,
                                     @RequestParam(value = "identification", required = false) String user_id,
                                     Model model) {

        System.out.println("customer id : " + user_id );
        if(subscribe != null && subscribe.length >0)
            for(String service_id : subscribe)
                serviceDAO.addService(Long.valueOf(service_id), Long.valueOf(user_id));
        System.out.println(cancel);
        if(cancel != null && cancel.length>0)
            for(String service_id : cancel) {
                serviceDAO.unsubscribeService(Long.valueOf(service_id), Long.valueOf(user_id));
            }
        Customer cust = customerDAO.getCustomer(Long.parseLong(user_id), serviceDAO, packageDao);
        String type = "customerServicesUpdate";
        model.addAttribute("type", type);
        model.addAttribute("CUSTOMER", cust);
        return "dashboard/services";
    }


    @RequestMapping("/customers")
    public String customer(Model model) {
        User dude = getAuthenticated();
        long user_id = dude.getId();

        List<Customer> myCustomers = customerDAO.getCustomers(user_id, serviceDAO, packageDao);
        System.out.println(myCustomers);
        model.addAttribute("CUSTOMERS", myCustomers);
        return "dashboard/customers";
    }
    
    @RequestMapping("/bill")
    public String bill(Model model){
       User dude = getAuthenticated();
       long user_id = dude.getId();
       Customer cust = customerDAO.getCustomer(user_id, serviceDAO, packageDao);
        model.addAttribute("CUSTOMER", cust);

       return "dashboard/bill";
    }
    
    @RequestMapping("/packages")
    public String packages(Model model){
        User dude = getAuthenticated();
        long user_id = dude.getId();
        Customer cust = customerDAO.getCustomer(user_id, serviceDAO, packageDao);
        String type = "customerPackagesUpdate";
        model.addAttribute("type", type);
        model.addAttribute("CUSTOMER", cust);

       return "dashboard/packages";
    }

    @RequestMapping(value={"/customerPackagesUpdate"}, method = RequestMethod.POST)
    public String customerUpdatePackageSubscription(@RequestParam(value = "subscribe", required = false) String[] subscribe,
                                             @RequestParam(value = "cancel",    required = false) String[] cancel,
                                             @RequestParam(value = "identification", required = false) String user_id,
                                             Model model) {
        System.out.println("customer id : " + user_id );
        if(subscribe != null && subscribe.length >0)
            for(String package_id : subscribe)
                packageDao.addPackage(Long.valueOf(package_id), Long.valueOf(user_id));

        if(cancel != null && cancel.length>0)
            for(String package_id : cancel)
            packageDao.unsubscribePackage(Long.valueOf(package_id), Long.valueOf(user_id));
        Customer cust = customerDAO.getCustomer(Long.parseLong(user_id), serviceDAO, packageDao);
        String type = "customerPackagesUpdate";
        model.addAttribute("type", type);
        model.addAttribute("CUSTOMER", cust);
        return "dashboard/packages";
    }
    @RequestMapping(value={"/setThreshold"}, method = RequestMethod.POST)
    public String setThreshold(@RequestParam(value = "threshold", required = false) String threshold, Model model){
        User dude = getAuthenticated();
        long user_id = dude.getId();

        if(threshold != null){
            userDAO.setThreshold(user_id, Double.parseDouble(threshold));
        }

        return "redirect:/dashboard/bill";
    }

    @RequestMapping(value={"/createCustomer"})
    public String createCustomer(User user){
        return "register/register";
    }
}