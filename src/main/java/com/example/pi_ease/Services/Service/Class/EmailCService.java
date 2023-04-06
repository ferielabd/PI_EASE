package com.example.pi_ease.Service.Class;

import com.example.pi_ease.DAO.Repositories.EmailCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailCService {
    private final JavaMailSender mailSender;
    private final EmailCRepository emailCRepository;

    @Autowired

    public EmailCService(JavaMailSender mailSender, EmailCRepository emailRepository) {
        this.mailSender = mailSender;
        this.emailCRepository = emailRepository;
    }

    public void sendSimpleMessage(String toRec, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lenders.mfi123@gmail.com");
        message.setTo(toRec);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

      /*  Email email = new Email(to, subject, text);
        emailRepository.save(email);*/
    }
}
