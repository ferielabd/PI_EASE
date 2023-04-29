package com.example.pi_ease.Services.Classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lenders.mfi123@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");


    }
    public void sendSimpleEmail1(String toEmail,
                                 String subject,
                                 List list
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("marzoukghassen6@gmail.com");
        message.setTo(toEmail);

        message.setSubject(subject);
        message.setText("check the claims which its ID is ="+list);


        mailSender.send(message);
        System.out.println("Mail Send...");


    }

}