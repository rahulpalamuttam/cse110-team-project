package com.googleit.telecom.dao;

import com.googleit.telecom.models.items.*;
import com.googleit.telecom.models.items.Package;

import java.util.List;

/**
 * Created by rahul on 2/24/15.
 */
public interface packageDAO {
    public List<Package> getPackages();
    public void insert(Package pack);
}
