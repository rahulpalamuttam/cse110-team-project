package com.googleit.telecom.models.users;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerRepresentativeTest {
	Customer cus;
	CustomerRepresentative crep;
	@Before
    public void setup(){
      crep = new CustomerRepresentative();
        cus = new Customer();
    }

	@Test
	public void testaddCustomer() throws Exception{
		List<Customer> clist;
		crep.addCustomer(cus);
		clist = crep.getCustomerList();
		assertTrue(clist.contains(cus));
	}
	
	@Test
	public void testgetAddress() throws Exception{
		crep.setAddress("100 google dr");
		assertEquals("100 google dr", crep.getAddress());
	}
	
	@Test
	public void testgetCustomer()
	{
		Customer testcus = new Customer();
		cus.setId((long) 123);
		crep.addCustomer(cus);
		testcus = crep.getCustomer((long)123);
		assertEquals((long)123, testcus.getId());
	}

}
