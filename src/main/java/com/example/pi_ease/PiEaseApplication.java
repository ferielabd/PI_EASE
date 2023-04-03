package com.example.pi_ease;

import com.example.pi_ease.DAO.Config.twilioConfig;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling

public class PiEaseApplication {
    //@Autowired
    //private twilioConfig twilioconfig;

    //@PostConstruct
    //public void initTwilio(){
      //  Twilio.init(twilioconfig.getAccountSid(),twilioconfig.getAuthToken());
    //}

    public static void main(String[] args) {
        SpringApplication.run(PiEaseApplication.class, args);
    }

}
