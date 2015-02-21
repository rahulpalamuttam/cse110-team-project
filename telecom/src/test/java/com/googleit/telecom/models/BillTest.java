package com.googleit.telecom.models;

import com.googleit.telecom.models.items.Service;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillTest {

    Bill bill;
    Service service;
    @Before
    public void Setup(){
        bill = new Bill();
        service = new Service();
        service.setPrice(10.00);
    }

    @Test
    public void testPay() throws Exception {
        bill.pay(10.00);
    }

    @Test
    public void testAddItem() throws Exception {
        bill.addItem(service);
    }

    @Test
    public void testDeleteItem() throws Exception {
        bill.deleteItem(service);
    }
}