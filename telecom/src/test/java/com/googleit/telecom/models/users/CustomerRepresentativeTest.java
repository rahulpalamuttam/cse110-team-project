package com.googleit.telecom.models.users;

import org.junit.Before;
import java.util.List;
import org.junit.Test;
import java.lang.Exception;
import static org.junit.Assert.*;

public class CustomerRepresentativeTest {
	Customer cus= new Customer();
	CustomerRepresentative crep;
	@Before
    public void setup(){
      crep = new CustomerRepresentative();
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
		cus.setID((long) 123);
		crep.adCustomer(cus);
		testcus = crep.getCustomer((long)123);
		assertTrue((long)123, testcus.getID());
	}

}
