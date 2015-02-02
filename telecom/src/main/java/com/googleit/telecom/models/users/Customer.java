package com.googleit.telecom.models.users;

import com.googleit.telecom.models.*;
import com.googleit.telecom.models.Package;

import java.util.ArrayList;


/**
 * Created by rahul on 2/1/15.
 */
public class Customer extends User {
    private ArrayList<Service> subscribedServices;
    private ArrayList<Package> subscribedPackages;
    private String address;
    private Bill customerBill;
    public Customer(){
        subscribedServices = new ArrayList<Service>();

    }

    /**
     * Adds a new service to the list of services
     * that this customer is subscribed for
     * @param newService
     */
    public void AddService(Service newService){
        subscribedServices.add(newService);
        customerBill.addItem(newService);
    }

    /**
     * Deletes a specific service from the list
     * of services that this customer is subscribed for.
     * @param noService
     */
    public void DeleteService(Service noService){
        subscribedServices.remove(noService);
        customerBill.deleteItem(noService);

    }

    /**
     * Adds a new package to the list of packages
     * that this customer is subscribed for.
     * @param newPackage
     */
    public void AddPackage(Package newPackage){
        subscribedPackages.add(newPackage);
    }

    /**
     * Deletes a specific package from the list
     * of packages that this customer is subscribed for.
     */

    public void DeletePackage(Package newPackage){
        subscribedPackages.remove(newPackage);
    }

    public void PayBill(Double amount){
        customerBill.pay(amount);
    }

    public void changeAddress(String newAddress){
        this.address = newAddress;
    }


}
