package com.googleit.telecom.Notifier.RuleObject;

import com.googleit.telecom.Notifier.ObserverPattern.AbstractObserver;
import com.googleit.telecom.configs.MailConfig;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;


import javax.mail.internet.MimeMessage;


/**
 * Created by rahul on 3/11/15.
 */

@Controller
public class CustomerAction implements Action {
    private JavaMailSender mailSender;

    private String from 	= "git110winter15@gmail.com";
    private String subject	= "Balance Notification";

    @Override
    public void execute(AbstractObserver observer) {
        System.out.println(observer.getEmail() + " You are above your threshold limit");
        try {
            mailSender = MailConfig.getInstance();
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setTo("git110winter15@gmail.com");
            messageHelper.setText("You are above you threshold limit");
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);

            mailSender.send(message);
        } catch(Exception e){
            System.out.println(e);
        }
    }
}

