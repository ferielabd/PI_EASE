package com.example.pi_ease.Services.Classes;


import com.example.pi_ease.DAO.Entities.Transaction;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.transactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {
    @Autowired
    private JavaMailSender mailSender;
    private transactionRepo transactionrepo;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        //Transaction transaction = transactionrepo.findById(transactionId).get();
        //User user = transaction.getUser();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ibtissem.ltifi@esprit.tn");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }
}

