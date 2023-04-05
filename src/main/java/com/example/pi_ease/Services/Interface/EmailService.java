package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.EmailDetails;
import com.example.pi_ease.DAO.Entities.User;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);

    String getEmailContent(User user)throws IOException, TemplateException;
    String getEmailContentRegister(User user)throws IOException, TemplateException;
    public void sendEmail(User user)throws IOException, TemplateException, MessagingException;
    public void sendEmailRegister(User user)throws IOException, TemplateException, MessagingException;


}
