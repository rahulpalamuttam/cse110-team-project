package com.googleit.telecom.configs;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class MailConfig {
    private static JavaMailSender mailSender;


    // This class does not allows instantiation
    private MailConfig() {}

    private static JavaMailSender createInstance() {
        JavaMailSenderImpl msi = new JavaMailSenderImpl();
        Properties props = new Properties();
        msi.setHost("smtp.gmail.com");
        msi.setUsername("huliopalam@gmail.com");
        msi.setPassword("pword");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required","ture");
        msi.setJavaMailProperties(props);
        mailSender = msi;

        return mailSender;
    }

    public static JavaMailSender getInstance() {
        return mailSender != null ? mailSender : createInstance();
    }
}
