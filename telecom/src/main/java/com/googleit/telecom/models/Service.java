package com.googleit.telecom.models;

import java.util.Date;

/**
 * Created by rahul on 2/1/15.
 */
public class Service {
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private Date serviceStartDate;
    private Date serviceEndDate;
    private String serviceID;
    private Rule serviceRule;

    public void setPrice(Double price){
        this.servicePrice = price;
    }

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
    public void setServiceID(String newId){
        this.serviceID = newId;
    }

    public String getServiceID(){
        return this.serviceID;
    }

    public void setRule(Rule newRule){
        this.serviceRule = newRule;
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
