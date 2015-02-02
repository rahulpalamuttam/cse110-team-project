package com.googleit.telecom.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * Created by rahul on 2/1/15.
 */
public class Service {
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private Date serviceStartDate;
    private Date serviceEndDate;
    private UUID serviceID;
    private List<Rule> serviceRules;

    public Service(){}
    public Service(List<Rule> rules, String name,
                   String description, Date start,
                   Date end, Double price){

        serviceRules = rules;
        serviceName = name;
        serviceDescription = description;
        serviceStartDate = start;
        serviceEndDate = end;
        servicePrice = price;
        // need to check if UUID's have been repeated
        serviceID = UUID.randomUUID();

    }
    public void setPrice(Double price){
        this.servicePrice = price;
    }
    public Double getPrice() { return this.servicePrice;}

    public void setDuration(Date start, Date end){
        this.serviceStartDate = start;
        this.serviceEndDate = end;
    }

    public void setServiceDescription(String description){
        this.serviceDescription = description;
    }

    public void setServiceName(String name){
        serviceName = name;
    }

    /**
     * The service ID is a unique identifier.
     * It will be the basis of the .equals method.
     */
    public void setServiceID(UUID newId){
        this.serviceID = newId;
    }

    public UUID getServiceID(){
        return this.serviceID;
    }

    public void addRule(List<Rule> newRules){
        this.serviceRules = newRules;
    }

    /**
     * Used by find operations in java api data-structures
     * @param newService
     * @return
     */
    public boolean equals(Service newService){
        return (newService.serviceID.equals(this.serviceID));
    }

}
