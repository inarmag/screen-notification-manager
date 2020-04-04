package com.sqreen.notification.manager.configurations;

import com.sqreen.notification.manager.filters.SignatureVerificationFilter;
import com.sqreen.notification.manager.services.NotificationService;
import com.sqreen.notification.manager.services.impl.EmailNotificationService;
import com.sqreen.notification.manager.services.impl.SlackNotificationService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfiguration {

    @Bean
    public FilterRegistrationBean<SignatureVerificationFilter> loggingFilter(){

        FilterRegistrationBean<SignatureVerificationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SignatureVerificationFilter());
        registrationBean.addUrlPatterns("/notification");

        return registrationBean;
    }


    @Bean
    public List<NotificationService> getNotificationSenderServices () {

        List<NotificationService> notificationServices = new ArrayList<>();

        notificationServices.add(new SlackNotificationService());
        notificationServices.add(new EmailNotificationService());

        return notificationServices;

    }

}
