package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.SmsRequest;
import com.example.pi_ease.Services.Classes.TwilioSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/sms")
public class TwilioController {
    private final TwilioSer service;

    @Autowired
    public TwilioController(TwilioSer service) {
        this.service = service;
    }

    @PostMapping
    public void sendSms(@Validated @RequestBody SmsRequest smsRequest) {
        service.sendSms(smsRequest);
    }
}
