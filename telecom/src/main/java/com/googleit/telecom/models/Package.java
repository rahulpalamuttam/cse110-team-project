package com.googleit.telecom.models;

import java.util.ArrayList;

/**
 * A package is a collection of services
 * Created by rahul on 2/1/15.
 */
public class Package {
    private ArrayList<Service> PackagedServices;

    /**
     * Called when we want to package together a new service
     * @param services
     */
    public Package(Service... services){
        PackagedServices = new ArrayList<Service>();
        for(Service newService : services){
            PackagedServices.add(newService);
        }
    }
}
