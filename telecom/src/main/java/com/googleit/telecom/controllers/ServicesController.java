
package com.googleit.telecom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.internet.MimeMessage;

@Controller
public class ServicesController {
    @Autowired
    private JavaMailSender mailSender;

    private String from 	= "jjl112ucsd@gmail.com";
    private String subject	= "Subject";

    @RequestMapping("/services")
    public String login(Model model) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setTo("jjl112ucsd@gmail.com");
            messageHelper.setText("HelloWorld");
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);	// 메일제목은 생략이 가능하다

            mailSender.send(message);
        } catch(Exception e){
            System.out.println(e);
        }

        return "pages/services";

    }
}
