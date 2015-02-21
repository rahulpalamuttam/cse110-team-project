package com.googleit.telecom.models;

import com.googleit.telecom.models.items.Service;

/**
 * Created by rahul on 2/1/15.
 */
public class Bill {
    private Double totalAmount;
    private Double amountPaid;
    private Double amountLeft;

    /**
     * Customer call pay to pay the bill.
     * There needs to be checks to ensure
     * that the customer does not over pay
     * the bill.
     * @param payment
     */
    public void pay(Double payment){
        // There needs to be preliminary checks
        amountLeft -= payment;
        amountPaid += payment;
    }
    
    public double getTotalAmount(){
    	return this.totalAmount;
    }

    /**
     *
     * @param service
     */
    public void addItem(Service service){
        Double amount = service.getPrice();
        totalAmount += amount;
        amountLeft += amount;
    }

    public void deleteItem(Service service){
        Double amount = service.getPrice();
        totalAmount -= amount;
        amountLeft -= amount;
    }
}
