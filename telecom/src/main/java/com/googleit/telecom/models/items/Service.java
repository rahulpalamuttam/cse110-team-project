package com.googleit.telecom.models.items;

import java.util.Date;

/**
 * Created by rahul on 2/1/15.
 */
public class Service implements Buyable{
    public static long total_service = 0;

    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private Long serviceID;
    private Date start_date;
    private Date end_date;

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setDuration(Date start_date, Date end_date){
        this.start_date = start_date;
        this.end_date = end_date;
    }
    public Service(){}
    public Service(String name, String description, double price){

        this.serviceName = name;
        this.serviceDescription = description;
        this.servicePrice = price;
    }

    public void setPrice(double price){
        this.servicePrice = price;
    }
    public double getPrice() { return this.servicePrice;}


    public void setServiceDescription(String description){
        this.serviceDescription = description;
    }

    public void setServiceName(String name){
        serviceName = name;
    }

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

    public boolean equals(Service newService){
        return (newService.serviceID.equals(this.serviceID));
    }


    @Override
    public String toString() {
        return getServiceName();
    }
}
