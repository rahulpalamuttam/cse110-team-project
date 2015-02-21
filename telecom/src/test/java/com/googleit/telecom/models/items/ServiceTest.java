package com.googleit.telecom.models.items;

import com.googleit.telecom.models.Bill;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
}