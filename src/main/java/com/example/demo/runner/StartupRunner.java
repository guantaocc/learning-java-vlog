package com.example.demo.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {

    @Order(1)
    @Override
    public void run(ApplicationArguments args) {
        System.out.println("选项参数：" + args.getOptionNames());
        System.out.println("mode 参数：" + args.getOptionValues("mode"));
        System.out.println("普通参数：" + args.getNonOptionArgs());
    }
}
