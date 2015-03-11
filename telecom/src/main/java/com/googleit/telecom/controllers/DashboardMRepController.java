package com.googleit.telecom.controllers;

import com.googleit.telecom.dao.ServiceDAO;
import com.googleit.telecom.dao.packageDAO;
import com.googleit.telecom.models.items.Package;
import com.googleit.telecom.models.items.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardMRepController {
	@Autowired
    private ServiceDAO serviceDAO;
	@Autowired
	private packageDAO packageDAO;
	
	@RequestMapping(value="/serviceslist")
    public String serviceList(Model model) {
		List<Service> allServices = serviceDAO.getAllService();
		model.addAttribute("allServices",allServices);
        return "dashboard/serviceList";
    }

	@RequestMapping(value="/packageslist")
    public String packageList(Model model) {
		List<Package> allPackages = packageDAO.getAllPackage();
		model.addAttribute("allPackages",allPackages);
        return "dashboard/packageList";
    }


	@RequestMapping(value="/addPackage", method = RequestMethod.GET)
	public String serviceChoices(Model model) {
		List<Service> allServices = serviceDAO.getAllService();
		model.addAttribute("allServices",allServices);
		return "dashboard/addPackage";
	}


	@RequestMapping(value="/addPackage", method = RequestMethod.POST)
	public String addPackage(@RequestParam(value = "add", required = false) Long[] subscribe,
                             Package pack) {
		packageDAO.createPackage(pack);
        if(subscribe != null && subscribe.length >0)
            for(long service_id : subscribe)
                packageDAO.addService(pack.getPackageID(), service_id);
		return "redirect:/dashboard/packageslist";
	}

	
	@RequestMapping(value="/addService")
	public String addServiceForm() {
		return "dashboard/addService";
	}
    
	@RequestMapping(value="/addService", method = RequestMethod.POST)
	public String addService(Service service) {
		serviceDAO.createServie(service);
		return "redirect:/dashboard/serviceslist";
	}

    @RequestMapping(value="/modifyPackage", method = RequestMethod.POST)
    public String modifyPackage(Model model, @RequestParam(value="package_id") long packageID) {

        List<Service> subscribedServices = packageDAO.getSubscribedService(packageID);
        List<Service> unsubscribedServices = packageDAO.getUnsubscribedService(packageID);
        model.addAttribute("subscribedServices", subscribedServices);
        model.addAttribute("unsubscribedServices", unsubscribedServices);
        model.addAttribute("type", "updatePackage");
        model.addAttribute("package_id", packageID);
        return "dashboard/modifyPackage";
    }

    @RequestMapping(value="/updatePackage", method = RequestMethod.POST)
    public String updatePackage(Model model,@RequestParam(value = "subscribe", required = false) Long[] subscribe,
                                @RequestParam(value = "cancel",    required = false) Long[] cancel,
                                @RequestParam(value = "package_id") long packageID) {

        System.out.println("PackageID : " + packageID);
        System.out.println("Subscribe : " + subscribe);
        System.out.println("Cancel : " + cancel);

        if(subscribe != null && subscribe.length >0)
            for(long service_id : subscribe)
                packageDAO.addService(packageID,service_id);

        if(cancel != null && cancel.length>0)
            for(long service_id : cancel)
                packageDAO.unsubscribeService(packageID, service_id);

        List<Service> subscribedServices = packageDAO.getSubscribedService(packageID);
        List<Service> unsubscribedServices = packageDAO.getUnsubscribedService(packageID);
        model.addAttribute("subscribedServices", subscribedServices);
        model.addAttribute("unsubscribedServices", unsubscribedServices);
        model.addAttribute("type", "updatePackage");
        model.addAttribute("package_id", packageID);

        return "dashboard/modifyPackage";
    }


}