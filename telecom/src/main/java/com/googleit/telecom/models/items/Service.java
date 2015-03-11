package com.googleit.telecom.models.items;

/**
 * Created by rahul on 2/1/15.
 */
public class Service implements Buyable{
    public static long total_service = 0;

    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private Long serviceID;


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
