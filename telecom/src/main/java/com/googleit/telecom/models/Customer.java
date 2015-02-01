package com.googleit.telecom.models;

import java.util.ArrayList;


/**
 * Created by rahul on 2/1/15.
 */
public class Customer extends User {
    private ArrayList<Service> subscribedServices;

    public Customer(){
        subscribedServices = new ArrayList<Service>();
    }

    /**
     * Adds a new service to the list of services
     * that this customer is paying for
     * @param newService
     */
    public void AddService(Service newService){
        subscribedServices.add(newService);
    }

    public void DeleteService(Service noService){
        subscribedServices.remove(noService);
    }



}
