package com.sqreen.notification.manager.services.impl;

import com.sqreen.notification.manager.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("EmailNotificationSender")
public class EmailNotificationService implements NotificationService {

    @Value("${sqreen.notification.mail.receiver}")
    private String email;


    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendNotification(String eventUrl) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);

        mail.setSubject("Sqreen security notification");
        mail.setText("You just received this event \n" + eventUrl);

        javaMailSender.send(mail);

        return true;
    }
}
