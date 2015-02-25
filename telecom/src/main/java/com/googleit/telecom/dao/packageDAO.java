package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.*;
import com.googleit.telecom.models.items.Package;

import java.util.List;

/**
 * Created by rahul on 2/24/15.
 */
public interface packageDAO {
    public List<Package> getUnsubscribedPackage(long user_id);
    public List<Package> getSubscribedPackage(long user_id);
    public void addPackage(long service_id, long user_id);
    public void unsubscribePackage(long service_id, long user_id);
    public List<Package> getAllPackage();
    public void createPackage(Package pack);
    public List<Service> getSubscribedService(long packageID);
    public List<Service> getUnsubscribedService(long packageID);
    public void addService(long package_id, long service_id);
    public void unsubscribeService(long package_id, long service_id);
}

