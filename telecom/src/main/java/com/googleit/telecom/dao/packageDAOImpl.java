package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.Package;
import com.googleit.telecom.models.items.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Created by rahul on 2/24/15.
 */
public class packageDAOImpl implements packageDAO {
    List<Package> packageList;
    long id = 0;

    public packageDAOImpl(){
        packageList = new ArrayList<Package>();
        Package freebie = new Package();
        freebie.setPackageName("Freebie Packages");
        freebie.setStartDate(new Date());
        freebie.setEndDate(new Date());
        freebie.setDescription("This is a freebie package, enjoy");
        freebie.setPrice(0.0);
        Package expensive = new Package();
        expensive.setPackageName("Expensive Packages");
        expensive.setDescription("This is an expensive package, please pay");
        expensive.setStartDate(new Date());
        expensive.setEndDate(new Date());
        this.insert(freebie);
        this.insert(expensive);
    }

    public void insert(Package pack){
        pack.setPackageID(id);
        packageList.add(pack);
        id++;
    }
    public List<Package> getPackages(){
        return packageList;
    }
    
}
