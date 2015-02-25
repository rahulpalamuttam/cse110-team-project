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
}
