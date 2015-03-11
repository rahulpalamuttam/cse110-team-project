package com.googleit.telecom.Notifier;

import java.util.Observer;

/**
 * Created by rahul on 3/10/15.
 */
public interface AbstractSubject {
    public void addObserver(AbstractObserver observer);
    public void deleteObserver(AbstractObserver observer);
    public void notifyObserver();
}

