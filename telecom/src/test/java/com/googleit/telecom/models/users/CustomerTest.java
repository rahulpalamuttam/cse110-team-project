package com.googleit.telecom.models.users;
import com.googleit.telecom.models.Bill;
import com.googleit.telecom.models.items.Service;
import org.junit.Before;
import org.junit.Test;

import java.lang.Deprecated;
import java.lang.Exception;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomerTest {
	
    Customer customer;
    Bill bill;
    Service service;

    @Before
    public void Setup(){
        customer = new Customer();
        customer.setBalance(100);
        service = new Service();
        service.setPrice(10.00);
        bill = new Bill();
    }

    @Test
    public void testAddService() throws Exception {
        customer.AddService(service);
        ArrayList<Service> list = customer.getSubscribedServices();
        assertTrue(list.contains(service));
    }

    @Test
    public void testDeleteService() throws Exception {
            customer.DeleteService(service);
            ArrayList<Service> list = customer.getSubscribedServices();
            assertFalse(list.contains(service));
    }

    @Test
    public void testAddPackage() throws Exception {

    }

    @Test
    public void testDeletePackage() throws Exception {

    }

    @Test
    public void testSetAddress() throws Exception {

    }

    @Test
    public void testSetCustomerBill() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {

    }

    @Test
    public void testgetBalance() throws Exception{

    }

    @Test
    public void testgetSubscribedServices() throws Exception{

    }

    @Test
    public void testgetSubscribedPackages() throws Exception{

    }


    @Test
    public void testPayBalance() throws Exception{
        double balance;
        balance = customer.getBalance();
        bill = customer.getCustomerBill();
        bill.setAmountLeft(10.00);
        customer.payBalance(balance);
        assertEquals(bill.getAmountLeft(), 10, 0);
    }


}