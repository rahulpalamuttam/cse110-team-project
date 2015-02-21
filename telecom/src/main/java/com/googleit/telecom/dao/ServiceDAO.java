package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.Service;

import java.util.List;

public interface ServiceDAO {
    public List<Service> getUnsubscribedService(long user_id);
    public List<Service> getSubscribedService(long user_id);
    public void addService(long service_id, long user_id);
    public void unsubscribeService(long service_id, long user_id);
    public List<Service> getAllService();
    public void createServie(Service service);
}
