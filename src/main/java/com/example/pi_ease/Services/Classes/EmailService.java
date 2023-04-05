package com.example.pi_ease.Services.Classes;

<<<<<<< HEAD
import com.example.pi_ease.DAO.Entities.Transaction;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.transactionRepo;
=======
>>>>>>> f8f32bed56b5f20912075f636c09ff1988a200d2
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
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
=======
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
        message.setFrom("marzoukghassen6@gmail.com");
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

>>>>>>> f8f32bed56b5f20912075f636c09ff1988a200d2
}
