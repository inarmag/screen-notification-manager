package com.sqreen.notification.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MainController {

    @GetMapping
    public String getSlash() {
        return "I am a slash";
    }

}
