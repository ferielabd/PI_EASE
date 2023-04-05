package com.example.pi_ease.DAO.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
@Data

public class twilioConfig {
    private String accountSid;
    private String authToken;
    private String trialNumber;


}
