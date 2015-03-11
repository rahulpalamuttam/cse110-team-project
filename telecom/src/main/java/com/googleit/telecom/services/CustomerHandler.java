package com.googleit.telecom.services;

import com.googleit.telecom.dao.ServiceDAO;
import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.models.items.Service;
import com.googleit.telecom.models.users.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class CustomerHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomerHandler.class);
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ServiceDAO serviceDAO;

    private Customer customer;

    public String serviceList(Model model, HttpSession session) {
        logger.info("serviceList method");

        Map<String,Object> u = (Map<String, Object>) session.getAttribute("user");
        customer = (Customer) userDAO.getUserByEmail((String)u.get("email"));
        logger.info("Fetched user => {}", customer);

        List<Service> subscribedServices = serviceDAO.getServices(customer.getSubscribedServices());
        logger.info("Fetched subscribed services", subscribedServices);

        model.addAttribute("subscribedServices", subscribedServices);
        return "dashboard/services/subscribed_service_list";
    }

    public String servicesForm(Model model) {
        logger.info("servicesForm method");
        
        return "dashboard/services/service_subscription_form";
    }

    public String subscribeServices(Long[] serviceIDs) {
        logger.info("subscribeServices method");
        logger.info("Querying Data. Subscribing services");

        for (Long id : serviceIDs)
            customer.subscribeService(id);

        logger.info("Subscription complete");

        return "redirect:dashboard/services";
    }

    public String packageList(Model model, HttpSession session) {
        logger.info("packageList method");

        return "dashboard/packages/subscribed_package_list";
    }


}
