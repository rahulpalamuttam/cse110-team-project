package com.googleit.telecom.models.users;

import com.googleit.telecom.models.Bill;
import com.googleit.telecom.models.items.Package;
import com.googleit.telecom.models.items.Service;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Created by rahul on 2/1/15.
 */
public class Customer extends User {
    private ArrayList<Service> subscribedServices;
    private ArrayList<Package> subscribedPackages;
    private Bill customerBill;
    private double balance;

    public Customer(String email, String password, String role) {
        super(email, password, role);

        this.subscribedServices = new ArrayList<>();
        this.subscribedPackages = new ArrayList<>();
        this.customerBill = new Bill();
        this.balance = 0;
    }

    public void subscribeService(Service newService){
        this.subscribedServices.add(newService);
    }

    public void unsubscribeService(Service noService){
        this.subscribedServices.remove(noService);
    }

    public void subscribePackage(Package newPackage){
        this.subscribedPackages.add(newPackage);
    }

    public void unsubscribePackage(Package newPackage){
        this.subscribedPackages.remove(newPackage);
    }

    public ArrayList<Service> getSubscribedServices() {
        return this.subscribedServices;
    }

    public ArrayList<Package> getSubscribedPackages() {
        return this.subscribedPackages;
    }

    public Bill getCustomerBill() {
        return this.customerBill;
    }

    public void setCustomerBill(Bill customerBill) {
        this.customerBill = customerBill;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
