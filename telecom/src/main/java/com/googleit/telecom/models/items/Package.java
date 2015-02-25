package com.googleit.telecom.models.items;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A package is a collection of services
 * Created by rahul on 2/1/15.
 */
public class Package {
    private ArrayList<Service> PackagedServices;
    private long packageID;
    private String packageName;
    private double price;
    private Date startDate;
    private Date endDate;
    private String description;

    public String getDescription() {
        return description;
    }

    public void insert(Service service) {
        PackagedServices.add(service);
    }

    public List<Service> getPackagedService(){
        return PackagedServices;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

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

    public long getPackageID() {
        return packageID;
    }

    public void setPackageID(long packageID) {
        this.packageID = packageID;
    }

    /**
     * Called when java collections API needs to
     * do comparisons of packages
     */
    public boolean equals(Package comparePackage){
        return (this.packageID == (comparePackage.packageID));
    }
}
