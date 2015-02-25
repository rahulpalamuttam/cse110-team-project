package com.googleit.telecom.controllers;

import com.googleit.telecom.dao.ServiceDAO;
import com.googleit.telecom.dao.packageDAO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googleit.telecom.models.items.Service;
import com.googleit.telecom.models.items.Package;

@Controller
@RequestMapping("/dashboard")
public class DashboardMRepController {
	@Autowired
    private ServiceDAO serviceDAO;
	@Autowired
	private packageDAO packageDAO;
	
	@RequestMapping(value="/serviceslist", method = RequestMethod.GET)
    public String serviceList(Model model) {
		List<Service> allServices = serviceDAO.getAllService();
		model.addAttribute("allServices",allServices);
        return "dashboard/serviceList";
    }

	@RequestMapping(value="/packageslist", method = RequestMethod.GET)
    public String packageList(Model model) {
		List<Package> allPackages = packageDAO.getPackages();
		model.addAttribute("allPackages",allPackages);
        return "dashboard/packageList";
    }
	
	
	@RequestMapping(value="/addServiceToPackage", method = RequestMethod.GET)
    public String addServiceToPackage(Model model) {
		List<Service> existingServices;
		List<Service> allServices = serviceDAO.getAllService();
		
		
		
		
        return "dashboard/addServiceToPackage";
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