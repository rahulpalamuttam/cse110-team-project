package com.googleit.telecom.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
    public String home() {
        return "dashboard/home";
    }

    @RequestMapping(value="/services")
    public String service() {
        return "dashboard/services";
    }

}
