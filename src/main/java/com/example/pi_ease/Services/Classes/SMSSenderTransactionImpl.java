package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Config.twilioConfig;
import com.example.pi_ease.Services.Interfaces.ISMSSenderTransaction;
import com.example.pi_ease.dto.SMSRequestTransaction;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;

import com.twilio.type.PhoneNumber;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;
import org.slf4j.Logger;
@Service

public class SMSSenderTransactionImpl implements ISMSSenderTransaction {


    private static final Logger LOGGER =  LoggerFactory.getLogger(SMSSenderTransactionImpl.class);

    private final twilioConfig twilioConfiguration;

    @Autowired
    public SMSSenderTransactionImpl(twilioConfig twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public String sendSms(SMSRequestTransaction smsRequest) {
        String otp = generateOTP();
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(twilioConfiguration.getTrialNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}");
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid number");

        }
        return otp;
    }

    public String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return  true;
    }
}