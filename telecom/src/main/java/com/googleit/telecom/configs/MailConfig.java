package com.googleit.telecom.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public static JavaMailSender mailSender(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties props = new Properties();
        mailSender.setHost("" +
                "smtp.gmail.com");
        mailSender.setUsername("jjl112ucsd@gmail.com");
        mailSender.setPassword("");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required","ture");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(props);

        return mailSender;

    }


}
