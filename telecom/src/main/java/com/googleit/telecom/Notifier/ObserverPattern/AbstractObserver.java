package com.googleit.telecom.Notifier.ObserverPattern;

/**
 * Created by rahul on 3/10/15.
 */
public interface AbstractObserver {
    public void update(String message);
    public String getEmail();
}
