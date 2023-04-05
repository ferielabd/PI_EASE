package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Transaction;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.transactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    private transactionRepo transactionrepo;

    public void sendSimpleEmail(Integer transactionId
    ) {
        Transaction transaction = transactionrepo.findById(transactionId).get();
        User user = transaction.getUser();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ibtissem.ltifi@esprit.tn");
        message.setTo(user.getMail());
        message.setText("Bonjour " + user.getFirst_name() + ",\n\nNous vous informons que votre transaction a été effectuée avec succès.\n\nCordialement,\nL'équipe de notre entreprise");
        ;
        message.setSubject("Transaction réussie");
        mailSender.send(message);
    }
}
