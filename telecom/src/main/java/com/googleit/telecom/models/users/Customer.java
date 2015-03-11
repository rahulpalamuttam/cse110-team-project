package com.googleit.telecom.models.users;

import com.googleit.telecom.models.Notifier.Bill;

import java.util.ArrayList;


/**
 * Created by rahul on 2/1/15.
 */
public class Customer extends User {
    private ArrayList<Long> subscribedServices;
    private ArrayList<Long> subscribedPackages;
    private Bill customerBill;
    private double balance;

    public Customer(){
        this.customerBill = new Bill();
    }
    public Customer(String email, String password, String role) {
        super(email, password, role);

        this.subscribedServices = new ArrayList<>();
        this.subscribedPackages = new ArrayList<>();
        this.customerBill = new Bill();
        this.balance = 0;
    }

    public void subscribeService(Long serviceId){
        this.subscribedServices.add(serviceId);
    }

    public void unsubscribeService(Long serviceId){
        this.subscribedServices.remove(serviceId);
    }

    public void subscribePackage(Long packageId){
        this.subscribedPackages.add(packageId);
    }

    public void unsubscribePackage(Long packageId){
        this.subscribedPackages.remove(packageId);
    }

    public ArrayList<Long> getSubscribedServices() {
        return this.subscribedServices;
    }

    public ArrayList<Long> getSubscribedPackages() {
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
