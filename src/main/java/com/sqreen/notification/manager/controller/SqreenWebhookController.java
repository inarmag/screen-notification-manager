package com.sqreen.notification.manager.controller;

import com.sqreen.notification.manager.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController("/notification")
public class SqreenWebhookController {

    @Autowired
    private List<NotificationService> notificationServices;

    @PostMapping
    public void receiveNotification(@RequestBody String message) {

        if (StringUtils.isNotBlank(message)) {
            log.info("I got this :: "+message);
            notificationServices.forEach(senderService -> senderService.sendNotification(message));
        }
    }

}
