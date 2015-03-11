package com.googleit.telecom.Factories;

import com.googleit.telecom.models.items.Buyable;
import com.googleit.telecom.models.items.BuyableType;
import com.googleit.telecom.models.items.Package;
import com.googleit.telecom.models.items.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by rahul on 3/8/15.
 */

/**
 * The BuyableFactory takes the result of a query as a Map<String, Object> and
 * returns the type of Buyable specified by the BuyableType.
 */
public class BuyableFactory {
    public Buyable getService(Map<String, Object> tuple){
        Service service = new Service();
        service.setServiceID((Long)tuple.get("service_id"));
        service.setServiceName((String) tuple.get("service_name"));
        service.setDuration((Date)tuple.get("start_date"), (Date)tuple.get("end_date"));
        service.setPrice((Double)tuple.get("price"));
        service.setServiceDescription((String)tuple.get("service_description"));
        return service;
    }

    public Buyable getPackage(Map<String, Object> tuple){
        Package pkg = new Package();
        pkg.setPackageID((Long) tuple.get("package_id"));
        pkg.setPackageName((String) tuple.get("package_name"));
        pkg.setPrice((Double) tuple.get("price"));
        pkg.setDescription((String) tuple.get("package_description"));
        return pkg;
    }

    public Buyable getBuyable(Map<String, Object> tuple, BuyableType type){
        Buyable buyable = null;
        switch(type){
            case SERVICE_TYPE:
                buyable = getService(tuple);
                break;
            case PACKAGE_TYPE:
                buyable = getPackage(tuple);
                break;
        }

        return buyable;

    }

    /**
     * Extracts the data from a SQL query and encapsulates it within
     * a List of Service Objects.
     * @param tuples
     * @return
     */
    public List<Buyable> getServiceList(List<Map<String, Object>> tuples){
        List<Buyable> services = new ArrayList<>();
        for(Map<String,Object> tuple : tuples){
            Service returned = (Service) this.getBuyable(tuple, BuyableType.SERVICE_TYPE);
            services.add(returned);
        }

        return services;
    }

    public List<Buyable> getPackageList(List<Map<String, Object>> tuples){
        List<Buyable> packages = new ArrayList<>();
        for(Map<String,Object> tuple : tuples){
            Package returned = (Package) this.getBuyable(tuple, BuyableType.PACKAGE_TYPE);
            packages.add(returned);
        }

        return packages;
    }

    public List<Buyable> getBuyableList(List<Map<String, Object>> tuples, BuyableType type){
        List<Buyable> buyableList = null;
        switch(type){
            case SERVICE_TYPE:
                buyableList = getServiceList(tuples);
                break;
            case PACKAGE_TYPE:
                buyableList = getPackageList(tuples);
                break;
        }

        return buyableList;
    }



}
