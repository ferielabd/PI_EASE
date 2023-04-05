package com.example.pi_ease.Services.Interfaces;

import com.example.pi_ease.dto.SMSRequestTransaction;

public interface ISMSSenderTransaction {
    String sendSms(SMSRequestTransaction smsRequest);

    String generateOTP();
}
