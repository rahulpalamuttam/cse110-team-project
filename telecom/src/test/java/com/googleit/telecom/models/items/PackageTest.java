package com.googleit.telecom.models.items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackageTest {
    Package pack;

    @Before
    public void setup(){
        pack = new Package();
    }

    @Test
    public void testGetPackageID() throws Exception {
        pack.setPackageID(123);
        assertEquals(123, pack.getPackageID());
    }


    @Test
    public void testEquals() throws Exception {
        Package packother = new Package();
        packother.setPackageID(123);
        pack.setPackageID(123);
        assertTrue(pack.equals(packother));
    }
}