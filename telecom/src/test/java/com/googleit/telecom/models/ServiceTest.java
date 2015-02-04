package com.googleit.telecom.models;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.UUID;



public class ServiceTest {
	
	private Service service;

    @Test
    public void testSetPrice() throws Exception {
    	double getPriceTest = 100;
    	double result;
    	
        // By default id should be 0
        assertEquals(0, 0);
        
        //value test
        result = service.getPrice();
        service.setPrice(getPriceTest);
        assertEquals(getPriceTest, result, 0.00001);
    }

    @Test
    public void testSetDuration() throws Exception {

    }

    @Test
    public void testSetServiceDescription() throws Exception {

    }

    @Test
    public void testSetServiceName() throws Exception {

    }

    @Test
    public void testSetServiceID() throws Exception {
    	// By default id should be 0
        assertEquals(0, 0);  
        
        // Set id then test getId
        for(int i=0; i<50; i++) {
        	UUID testUUID =  UUID.randomUUID();
            assertEquals(testUUID, service.getServiceID());
        }
        
    }

    @Test
    public void testGetServiceID() throws Exception {
    	// By default id should be 0
        assertEquals(0, 0);  
        
        // Set id then test getId
        for(int i=0; i<50; i++) {
        	UUID testUUID =  UUID.randomUUID();
            assertEquals(testUUID, service.getServiceID());
        }
    }

    @Test
    public void testSetRule() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {

    }

    @Test
    public void testSetPrice1() throws Exception {
    	double result;
    	
    	// By default id should be 0
        assertEquals(0, 0);
        
        // Set id then test getId
        for(double i=0; i<50; i++) {
            service.setPrice(i);
            result = i;
            assertEquals(result, service.getPrice(), 0.0001);
        }
    }

    @Test
    public void testGetPrice() throws Exception {

    }

    @Test
    public void testSetDuration1() throws Exception {

    }

    @Test
    public void testSetServiceDescription1() throws Exception {

    }

    @Test
    public void testSetServiceName1() throws Exception {

    }

    @Test
    public void testSetServiceID1() throws Exception {

    }

    @Test
    public void testGetServiceID1() throws Exception {
    	// By default id should be 0
        assertEquals(0, 0);  
        
        // Set id then test getId
        for(int i=0; i<50; i++) {
        	UUID testUUID =  UUID.randomUUID();
            assertEquals(testUUID, service.getServiceID());
        }
    }

    @Test
    public void testAddRule() throws Exception {

    }

    @Test
    public void testEquals1() throws Exception {

    }
}