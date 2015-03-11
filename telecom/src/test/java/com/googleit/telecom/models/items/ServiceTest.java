package com.googleit.telecom.models.items;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServiceTest {


	Service testService;

    @Before
    public void Setup(){
        testService = new Service();
    }
    @Test
    public void testGetPrice() throws Exception {
       double testIDPrice = 1000;
       testService.setPrice(testIDPrice);
       assertEquals(testIDPrice, testService.getPrice(), 0.000);
       
    }

    @Test
    public void testGetServiceID() throws Exception {
    	Long testID = 12536477L;
        testService.setServiceID(testID);
        assertEquals(testID, testService.getServiceID());
    }

    @Test
    public void testEquals() throws Exception {
       Service testerService = new Service();
        Service compareService = new Service();
        testerService.setServiceID((long) 1234);
        compareService.setServiceID((long) 1234);
       assertTrue(testerService.equals(compareService));
    }
    @Test
    public void testgetServiceDiscription() throws Exception{
        testService.setServiceDescription("good product");
        assertEquals("good product" , testService.getServiceDescription());
    }

    @Test
    public void testGetServiceName() throws Exception{
        testService.setServiceName("service");
        assertEquals("service", testService.getServiceName());
    }
    
    @Test
    public void testDuration() throws Exception{
        Date teststart = new Date("Mon May 04 09:51:52 CDT 2009");
        Date testend = new Date("Mon May 05 09:51:52 CDT 2009");
        testService.setDuration(teststart, testend);
        assertEquals(testService.getServiceStartDate(), teststart);
        assertEquals(testService.getServiceEndDate(), testend);
    }


}