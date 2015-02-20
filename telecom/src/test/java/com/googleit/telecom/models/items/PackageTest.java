package com.googleit.telecom.models.items;

import org.junit.Test;

import static org.junit.Assert.*;

public class PackageTest {
	
	Package onepackage;

    @Test
    public void testGetPackageID() throws Exception {
       String packageID = "ATT";
       onepackage.setPackageID(packageID);
       assertEquals(packageID, onepackage.getPackageID());
    }

    /* redundant
    @Test
    public void testSetPackageID() throws Exception {

    }*/

    @Test
    public void testEquals() throws Exception {
    	Service testerPackage = null;       
        boolean ans = true;
        boolean val;
        
        val = equals(testerPackage);
        assertEquals(val, ans);
    }
}