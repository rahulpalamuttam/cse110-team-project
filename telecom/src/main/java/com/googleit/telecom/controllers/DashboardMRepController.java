package com.googleit.telecom.controllers;

import com.googleit.telecom.dao.ServiceDAO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googleit.telecom.models.items.Service;

@Controller
@RequestMapping("/dashboard")
public class DashboardMRepController {
	@Autowired
    private ServiceDAO serviceDAO;
	
	@RequestMapping(value="/serviceslist", method = RequestMethod.GET)
    public String serviceList(Model model) {
		List<Service> allServices = serviceDAO.getAllService();
		model.addAttribute("allServices",allServices);
        return "dashboard/serviceList";
    }

	@RequestMapping(value="/packageslist", method = RequestMethod.GET)
    public String packageList(Model model) {
		List<Service> allPackage = serviceDAO.getAllPackage();
		model.addAttribute("allPackage",allPackages);
        return "dashboard/packageList";
    }

	@RequestMapping(value="/addService")
	public String addServiceForm(Service service) {
		return "dashboard/addService";
	}
	
	@RequestMapping(value="/addService", method = RequestMethod.POST)
	public String addService(Service service) {
		serviceDAO.createServie(service);
		return "redirect:/dashboard/serviceslist";
	}
}