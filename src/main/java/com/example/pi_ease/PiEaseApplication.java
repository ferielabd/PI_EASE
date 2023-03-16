package com.example.pi_ease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class PiEaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiEaseApplication.class, args);
    }

}
