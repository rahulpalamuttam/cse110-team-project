package com.googleit.telecom.Notifier;

import com.googleit.telecom.models.items.Buyable;
import com.googleit.telecom.models.items.Service;
import com.googleit.telecom.models.users.Customer;

import java.util.Observer;

/**
 * Created by rahul on 2/1/15.
 */
public class Bill implements AbstractSubject {
    private double totalAmount;
    private double amountPaid;
    private double amountLeft;
    private double threshold = MAX_INT;
    private AbstractObserver observer;
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
            // TODO :: We neuser_rolesed to make sure they dont overpay
        }

        amountLeft -= payment;
        amountPaid += payment;
        notifyObserver();
    }
    
    /**
     *
     * @param service
     */
    public void addItem(Buyable service){
        Double amount = service.getPrice();
        totalAmount += amount;
        amountLeft += amount;
        notifyObserver();
    }

    public void deleteItem(Buyable service){
        Double amount = service.getPrice();
        totalAmount -= amount;
        amountLeft += 10;
        notifyObserver();
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
        notifyObserver();
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
        notifyObserver();
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
        this.amountLeft = totalAmount;
        notifyObserver();
    }
    public void setThreshold(double threshold) {
        this.threshold = threshold;
        notifyObserver();
    }
    public double getThreshold() {
        return threshold;
    }

    // Observer pattern code
    @Override
    public void addObserver(AbstractObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(AbstractObserver observer) {
        this.observer = null;
    }

    @Override
    public void notifyObserver() {
        if(amountLeft > threshold) {
            observer.update("You are above your threshold limit by " + (totalAmount - threshold));
        } else {
            System.out.println("Amount left " + amountLeft);
            System.out.println("Threshold " + threshold);
            observer.update("Your account balance is in good condition!");
        }
    }
}
