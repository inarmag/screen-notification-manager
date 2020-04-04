package com.sqreen.notification.manager.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqreen.notification.manager.dtos.SlackMessage;
import com.sqreen.notification.manager.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("SlackNotificationSender")
public class SlackNotificationService implements NotificationService {

    @Value("${slack.incoming.webhook.link}")
    private String slackServiceIncomingUrl;

    @Value("${slack.channel}")
    private String slackChannel;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean sendNotification(String notification) {

        RestTemplate restTemplate = new RestTemplate();

        SlackMessage slackMessage = SlackMessage.builder().channel(slackChannel).text(notification).username("Sqreen").build();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(slackMessage), headers);

            restTemplate.exchange(slackServiceIncomingUrl, HttpMethod.POST, request, String.class);

        } catch (RuntimeException | JsonProcessingException e) {
            return false;
        }

        return true;
    }


}
