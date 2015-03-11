package com.googleit.telecom.Notifier.RuleObject;

import com.googleit.telecom.Notifier.ObserverPattern.AbstractObserver;


/**
 * Created by rahul on 3/11/15.
 */
public class CustomerAction implements Action{

    @Override
    public void execute(AbstractObserver observer) {
        System.out.println(observer.getEmail() + "You are above your threshold limit");
    }
}
