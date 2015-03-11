package com.googleit.telecom.models.Notifier;

import com.googleit.telecom.models.items.Buyable;

/**
 * Created by rahul on 2/1/15.
 */
public class Bill {
    private double totalAmount;
    private double amountPaid;
    private double amountLeft;

    /**
     * Customer call pay to pay the bill.
     * There needs to be checks to ensure
     * that the customer does not over pay
     * the bill.
     * @param payment
     */
    public void pay(double payment){
        // There needs to be preliminary checks
        if(amountLeft < payment){
            // TODO :: make sure that amountLeft is not less than 0;
            // TODO :: We need to make sure they dont overpay
        }

        amountLeft -= payment;
        amountPaid += payment;
    }
    
    /**
     *
     * @param service
     */
    public void addItem(Buyable service){
        Double amount = service.getPrice();
        totalAmount += amount;
        amountLeft += amount;
    }

    public void deleteItem(Buyable service){
        Double amount = service.getPrice();
        totalAmount -= amount;
        amountLeft -= amount;
    }

    public double getAmountLeft() {
        return amountLeft;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setAmountLeft(double amountLeft) {
        this.amountLeft = amountLeft;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
