package com.googleit.telecom.controllers;

import com.googleit.telecom.services.CustomerHandler;
import com.googleit.telecom.services.CustomerRepHandler;
import com.googleit.telecom.services.MarketingRepHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private CustomerHandler customerHandler;

    @Autowired
    private CustomerRepHandler customerRepHandler;

    @Autowired
    private MarketingRepHandler marketingRepHandler;

    @ModelAttribute("dashboard")
    public boolean init() {
        logger.info("Init method which controls the header");
        return true;
    }

    @RequestMapping(value = {"", "/", "/home"})
    public String home() {
        logger.info("Request Mapping to /dashboard home method");
        return "dashboard/home";
    }

    /******** Services ********/

    @RequestMapping(value = {"/services"})
    public String services(Model model, HttpSession session) {
        logger.info("services method");
        logger.info("Calling serviceList");
        return customerHandler.serviceList(model, session);
    }

    @RequestMapping(value = {"/subscribeServices"}, method= RequestMethod.POST)
    public String serviceSubscriptionTransaction(Model model,
                                                 HttpSession session,
                                                 @RequestParam(value = "subscribe") Long[] serviceIDs) {
        logger.info("serviceSubscriptionTranscation method");


        return customerHandler.subscribeServices(serviceIDs);
    }

    @RequestMapping(value = {"/serviceForm"})
    public String serviceForm(Model model) {
        logger.info("serviceForm method");
        return customerHandler.servicesForm(model);
    }




    /****** Packages ********/
    @RequestMapping(value = {"/packages"})
    public String packages(Model model, HttpSession session) {
        logger.info("packages method");
        logger.info("Calling packageList");
        return customerHandler.packageList(model, session);
    }

    @RequestMapping(value = {"/subscribePackages"})
    public String subscribePackages(Model model, HttpSession session) {
        logger.info("subscribePackages method");
        return null;
    }

    public String customers() {
        return null;
    }

    public String bill() {
        return null;
    }

}
