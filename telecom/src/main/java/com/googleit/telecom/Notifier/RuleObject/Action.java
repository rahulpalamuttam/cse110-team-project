package com.googleit.telecom.Notifier.RuleObject;

import com.googleit.telecom.Notifier.ObserverPattern.AbstractObserver;
/**
 * Created by rahul on 3/11/15.
 */
public interface Action {
    public void execute(AbstractObserver observer);
}
