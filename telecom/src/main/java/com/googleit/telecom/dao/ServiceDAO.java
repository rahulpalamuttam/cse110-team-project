package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.Service;

import java.util.ArrayList;
import java.util.List;

public interface ServiceDAO {
    public void createService(Service service);
    public List<Service> getServices(ArrayList<Long> serviceIDs);
    public List<Service> getAll();
}
