package com.example.pi_ease.DAO.Config;

import com.twilio.Twilio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;



@Configuration
public class TwilioInitializerCredi {

    private final static Logger log = LoggerFactory.getLogger(TwilioInitializerCredi.class);

    private final twilioConfig twilioConfiguration;

    @Autowired
    public TwilioInitializerCredi(twilioConfig twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
        log.info("Twilio initialized with account sid {}"+twilioConfiguration.getAccountSid());
    }
}

