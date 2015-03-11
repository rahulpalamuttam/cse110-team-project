package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.Service;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceRepository implements ServiceDAO {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ServiceRepository.class);
    Map<Long, Service> serviceRepo = new HashMap<>();

    @Override
    public void createService(Service service) {
        service.setServiceID(++Service.total_service);
        serviceRepo.put(service.getServiceID(), service);
    }

    @Override
    public List<Service> getServices(ArrayList<Long> serviceIDs) {
        List<Service> services= new ArrayList<>();
        for (Long id : serviceIDs) {
            services.add(serviceRepo.get(id));
        }

        return services;
    }

    @Override
    public List<Service> getAll() {
        return null;
    }
}
