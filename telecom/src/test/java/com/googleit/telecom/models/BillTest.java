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
        bill.setAmountLeft(10.00);
        bill.pay(10.00);
        assertEquals(bill.getAmountLeft(), 0,0);
        assertEquals(bill.getAmountPaid(), 10.00, 0);
    }

    @Test
    public void testAddItem() throws Exception {
        bill.addItem(service);
        assertEquals(bill.getTotalAmount(),10,0);
    }

    @Test
    public void testDeleteItem() throws Exception {
        bill.deleteItem(service);
        assertEquals(bill.getTotalAmount(),-10, 0);
    }
    @Test
    public void testAmountLeft() throws Exception{
        bill.setAmountLeft(10.00);
        assertEquals(10 , bill.getAmountLeft(),0);
    }
    @Test
    public void testTotalAmount() throws Exception{
        bill.setTotalAmount(10.00);
        assertEquals(10, bill.getTotalAmount(),0);
    }
    @Test
    public void testAmountPaid() throws Exception{
        bill.setAmountPaid(10.00);
        assertEquals(10,bill.getAmountPaid(),0 );
    }
}