package com.googleit.telecom.Notifier.RuleObject;

import com.googleit.telecom.Notifier.ObserverPattern.AbstractObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;


/**
 * Created by rahul on 3/11/15.
 */
public class CustomerAction implements Action{
    @Autowired
    private JavaMailSender mailSender;

    private String from 	= "jjl112ucsd@gmail.com";
    private String subject	= "Balance Notification";

    @Override
    public void execute(AbstractObserver observer) {
        System.out.println(observer.getEmail() + "You are above your threshold limit");
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setTo("jjl112ucsd@gmail.com");
            messageHelper.setText("You are above you threshold limit");
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);

            mailSender.send(message);
        } catch(Exception e){
            System.out.println(e);
        }
    }
}

