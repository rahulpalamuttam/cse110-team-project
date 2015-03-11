package com.googleit.telecom.Notifier;

import com.googleit.telecom.Notifier.ObserverPattern.AbstractObserver;
import com.googleit.telecom.Notifier.ObserverPattern.AbstractSubject;
import com.googleit.telecom.Notifier.RuleObject.*;
import com.googleit.telecom.models.items.Buyable;
import com.googleit.telecom.models.users.Customer;
import com.googleit.telecom.models.users.UserType;

/**
 * Created by rahul on 2/1/15.
 */
public class Bill implements AbstractSubject {
    private double totalAmount;
    private double amountPaid;
    private double amountLeft;
    private double threshold;
    private AbstractObserver observer;
    private Assessor assessor;
    private Action action;
    /**
     * Customer call pay to pay the bill.
     * There needs to be checks to ensure
     * that the customer does not over pay
     * the bill.
     * @param payment
     */
    public Bill(){
        assessor = new CustomerAssesor();
        action = new CustomerAction();
        observer = new Customer(this);
    }

    public Bill(AbstractObserver observer, UserType type){
        this.observer = observer;
        switch(type){
            case CUSTOMER:
                assessor = new CustomerAssesor();
                action = new CustomerAction();
                break;
            case CUSTOMER_REPRESENTATIVE:
                assessor = new CustomerAssesor();
                action = new CustomerRepresentativeAction();
                break;
        }
    }

    public void pay(double payment){
        // There needs to be preliminary checks
        if(amountLeft < payment){
            // TODO :: make sure that amountLeft is not less than 0;
            // TODO :: We neuser_rolesed to make sure they dont overpay
        }

        amountLeft -= payment;
        amountPaid += payment;
        applyRule();
    }
    
    /**
     *
     * @param service
     */
    public void addItem(Buyable service){
        Double amount = service.getPrice();
        totalAmount += amount;
        amountLeft += amount;
        applyRule();
    }

    public void deleteItem(Buyable service){
        Double amount = service.getPrice();
        totalAmount -= amount;
        amountLeft += 10;
        applyRule();
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
        applyRule();
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
        applyRule();
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
        this.amountLeft = totalAmount;
        applyRule();
    }
    public void setThreshold(double threshold) {
        this.threshold = threshold;
        applyRule();
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



    public void applyRule(){
        if(assessor.asses(amountLeft, threshold)) {
            notifyObserver("You are above your threshold limit by " + (totalAmount - threshold));
            action.execute(observer);
        } else {
            System.out.println("Amount left " + amountLeft);
            System.out.println("Threshold " + threshold);
            notifyObserver("Your account balance is in good condition!");
        }
    }
    @Override
    public void notifyObserver(String notification) {
        observer.update(notification);
    }
}
