package com.googleit.telecom.models.items;

import java.util.ArrayList;

/**
 * A package is a collection of services
 * Created by rahul on 2/1/15.
 */
public class Package {
    private ArrayList<Service> PackagedServices;
    private String packageID;

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

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    /**
     * Called when java collections API needs to
     * do comparisons of packages
     */
    public boolean equals(Package comparePackage){
        return (this.packageID.equals(comparePackage.packageID));
    }
}
