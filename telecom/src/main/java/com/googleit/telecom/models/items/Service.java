package com.googleit.telecom.models.items;

import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * Created by rahul on 2/1/15.
 */
public class Service implements Buyable{
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private Date serviceStartDate;
    private Date serviceEndDate;
    private Long serviceID;


    public Service(){}
    public Service(String name,
                   String description, Date start,
                   Date end, Double price, Long id){

        serviceName = name;
        serviceDescription = description;
        serviceStartDate = start;
        serviceEndDate = end;
        servicePrice = price;
        // need to check if UUID's have been repeated
        serviceID = id;

    }
    public void setPrice(double price){
        this.servicePrice = price;
    }
    public double getPrice() { return this.servicePrice;}

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
    public void setServiceID(Long newId){
        this.serviceID = newId;
    }

    public Long getServiceID(){
        return this.serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    /**
     * Used by find operations in java api data-structures
     * @param newService
     * @return
     */
    public boolean equals(Service newService){
        return (newService.serviceID.equals(this.serviceID));
    }

    public Date getServiceEndDate() {
        return serviceEndDate;
    }

    public Date getServiceStartDate() {
        return serviceStartDate;
    }
}
