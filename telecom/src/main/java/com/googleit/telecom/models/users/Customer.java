package com.googleit.telecom.models.users;

import com.googleit.telecom.Notifier.AbstractObserver;
import com.googleit.telecom.Notifier.Bill;
import com.googleit.telecom.models.items.Package;
import com.googleit.telecom.models.items.Service;

import java.util.ArrayList;


/**
 * Created by rahul on 2/1/15.
 */
public class Customer extends User implements AbstractObserver {
    private ArrayList<Service> subscribedServices;
    private ArrayList<Package> subscribedPackages;
    private String address;
    private double balance;
    private Bill customerBill;
    private String thresholdMessage = "";
    public Customer(){
        subscribedServices = new ArrayList<Service>();
        subscribedPackages = new ArrayList<Package>();
        customerBill = new Bill();
        customerBill.addObserver(this);
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
        customerBill.addItem(newPackage);
    }

    /**
     * Deletes a specific package from the list
     * of packages that this customer is subscribed for.
     */

    public void DeletePackage(Package newPackage){
        subscribedPackages.remove(newPackage);
    }


    public void changeAddress(String newAddress){
        this.address = newAddress;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Bill getCustomerBill() {
        return customerBill;
    }

    public void setCustomerBill(Bill customerBill) {
        this.customerBill = customerBill;
    }
    public boolean equals(Customer customer) {return this.getId() == customer.getId();}

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Service> getSubscribedServices() {
        return subscribedServices;
    }

    public ArrayList<Package> getSubscribedPackages() {
        return subscribedPackages;
    }

    public void payBalance(double amount){}

    @Override
    public void update(String thresholdmessage) {
        this.thresholdMessage = thresholdmessage;
    }

    public String getthresholdMessage(){
        return this.thresholdMessage;
    }
}
