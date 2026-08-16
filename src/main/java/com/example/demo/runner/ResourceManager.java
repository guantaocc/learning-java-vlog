package com.example.demo.runner;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class ResourceManager {

    @PreDestroy
    public void close() {
        System.out.println("释放应用资源");
    }
}
