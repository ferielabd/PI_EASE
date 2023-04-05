package com.example.pi_ease.Services.Interfaces;

import com.example.pi_ease.DAO.Entities.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
