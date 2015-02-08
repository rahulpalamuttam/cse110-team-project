package com.googleit.telecom.models.users;

import com.googleit.telecom.models.items.Service;

import java.util.Date;
import java.util.List;

/**
 * TODO: both a service and a package share very similar attributes.
 * Their only difference is that one is a collection of the other.
 * Their primary similarity is their buyability attributed
 * We need to implement a buyable interface!
 * Created by rahul on 2/1/15.
 */
public class SalesRep {
    /**
     * TODO:
     */
    public void CreateService(String serviceName,
                              String serviceDescription, Date start,
                              Date end, Double price){
        // the sales representative needs to create a service
        Service newOne = new Service(serviceName, serviceDescription, start, end, price);
        // What do we do with the service once its created?
    }

    public void CreatePackage(List<Service> services){

    }


}
