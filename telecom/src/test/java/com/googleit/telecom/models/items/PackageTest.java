package com.googleit.telecom.models.items;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import java.lang.Exception;

import static org.junit.Assert.*;

public class PackageTest {
    Package pack;
    Service serv;
    @Before
    public void setup(){
        serv = new Service();
        pack = new Package(serv);

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

    @Test
    public void testgetPrice() throws Exception{
        pack.setPrice(100.00);
        assertEquals(100, pack.getPrice(), 0);
    }

    @Test
    public void testsetStartDate() throws Exception{
        Date teststartdate = new Date ("Mon May 04 09:51:52 CDT 2009");
        pack.setStartDate(teststartdate);
        assertEquals(teststartdate, pack.getStartDate());
    }

    @Test
    public void testsetEndDate()throws Exception{
        Date testenddate = new Date ("Mon May 04 09:51:52 CDT 2009");
        pack.setEndDate(testenddate);
        assertEquals(testenddate, pack.getEndDate());
    }

    @Test
    public void testgetPackageName() throws Exception{
        pack.setPackageName("name");
        assertEquals("name", pack.getPackageName());
    }

    @Test
    public void testgetDescription() throws Exception{
        pack.setDescription("Description");
        assertEquals("Description", pack.getDescription());
    }

    @Test
    public void testInsert() throws Exception{
        Service item = new Service();
        List<Service> list = new ArrayList<Service>();
        pack.insert(item);
        list = pack.getPackagedService();
        assertTrue(list.contains(item));
    }

}