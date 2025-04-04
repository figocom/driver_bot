package com.figo.driver_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DriverBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriverBotApplication.class, args);
    }
}
