package com.googleit.telecom.Notifier.RuleObject;

/**
 * Created by rahul on 3/11/15.
 */
public class CustomerAssesor implements Assessor {
    @Override
    public boolean asses(double balance, double threshold) {
        return balance > threshold;
    }
}
