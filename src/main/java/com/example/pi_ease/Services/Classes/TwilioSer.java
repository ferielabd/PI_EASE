package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Config.twilioConfig;
import com.example.pi_ease.DAO.Entities.SmsRequest;
import com.example.pi_ease.Services.Interfaces.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@org.springframework.stereotype.Service
public class TwilioSer {
    private final SmsSender smsSender;

    @Autowired
    public TwilioSer(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
    //public Mono<PasswordResetResponseDto> sendOTPForPasswordReset(PasswordResetRequestDto passwordResetRequestDto) {
//
  //      PasswordResetResponseDto passwordResetResponseDto = null;
    //    try {
      //      PhoneNumber to = new PhoneNumber(passwordResetRequestDto.getPhoneNumber());
        //    PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
          //  String otp = generateOTP();
            //String otpMessage = "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";
            //Message message = Message
              //      .creator(to, from,
                //            otpMessage)
                  //  .create();
            //otpMap.put(passwordResetRequestDto.getUserName(), otp);
            //passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.DELIVERED, otpMessage);
        //} catch (Exception ex) {
          //  passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.FAILED, ex.getMessage());
        //}
        //return Mono.just(passwordResetResponseDto);
   // }

    //public Mono<String> validateOTP(String userInputOtp, String userName) {
       // if (userInputOtp.equals(otpMap.get(userName))) {
         //   otpMap.remove(userName,userInputOtp);
           // return Mono.just("Valid OTP please proceed with your transaction !");
        //} else {
          //  return Mono.error(new IllegalArgumentException("Invalid otp please retry !"));
       // }
    //}

    //6 digit otp


}
