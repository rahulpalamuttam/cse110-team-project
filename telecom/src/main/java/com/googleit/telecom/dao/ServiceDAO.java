package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.Service;

import java.util.List;

/**
 * Created by Joon on 2/10/2015.
 */
public interface ServiceDAO {
    public List<Service> getUnsubscribedService(int user_id);
    public List<Service> getSubscribedService(int user_id);

}
