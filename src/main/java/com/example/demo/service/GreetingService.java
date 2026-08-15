package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.config.AppProperties;

@Service
public class GreetingService {
    private final AppProperties appProperties;

    public GreetingService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String getMessage() {
        return appProperties.getGreeting()
                + " | "
                + appProperties.getName()
                + " | "
                + appProperties.getDescription();
    }
}
