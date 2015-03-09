package com.googleit.telecom.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

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


    public String services() {
        return null;
    }

    public String packages() {
        return null;
    }

    public String customers() {
        return null;
    }

    public String bill() {
        return null;
    }
}
