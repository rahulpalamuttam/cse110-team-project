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
    public Buyable CreateService(Map<String, Object> tuple){
        Service service = new Service();
        service.setServiceID((Long)tuple.get("service_id"));
        service.setServiceName((String) tuple.get("service_name"));
        service.setDuration((Date)tuple.get("start_date"), (Date)tuple.get("end_date"));
        service.setPrice((Double)tuple.get("price"));
        service.setServiceDescription((String)tuple.get("service_description"));
        service.setDuration( Integer.valueOf(tuple.get("duration").toString()));

        return service;
    }

    public Buyable CreatePackage(Map<String, Object> tuple){
        Package pkg = new Package();
        pkg.setPackageID((Long) tuple.get("package_id"));
        pkg.setPackageName((String) tuple.get("package_name"));
        pkg.setPrice((Double) tuple.get("price"));
        pkg.setDescription((String) tuple.get("package_description"));
        pkg.setDuration(Integer.valueOf(tuple.get("duration").toString()));
        return pkg;
    }

    public Buyable CreateBuyable(Map<String, Object> tuple, BuyableType type){
        Buyable buyable = null;
        switch(type){
            case SERVICE_TYPE:
                buyable = CreateService(tuple);
                break;
            case PACKAGE_TYPE:
                buyable = CreatePackage(tuple);
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
    public List<Buyable> CreateServiceList(List<Map<String, Object>> tuples){
        List<Buyable> services = new ArrayList<>();
        for(Map<String,Object> tuple : tuples){
            Service returned = (Service) this.CreateBuyable(tuple, BuyableType.SERVICE_TYPE);
            services.add(returned);
        }

        return services;
    }

    public List<Buyable> CreatePackageList(List<Map<String, Object>> tuples){
        List<Buyable> packages = new ArrayList<>();
        for(Map<String,Object> tuple : tuples){
            Package returned = (Package) this.CreateBuyable(tuple, BuyableType.PACKAGE_TYPE);
            packages.add(returned);
        }

        return packages;
    }

    public List<Buyable> CreateBuyableList(List<Map<String, Object>> tuples, BuyableType type){
        List<Buyable> buyableList = null;
        switch(type){
            case SERVICE_TYPE:
                buyableList = CreateServiceList(tuples);
                break;
            case PACKAGE_TYPE:
                buyableList = CreatePackageList(tuples);
                break;
        }

        return buyableList;
    }



}
