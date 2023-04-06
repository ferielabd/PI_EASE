package com.example.pi_ease.Services.Interfaces;

import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DTO.EmailDetailsDto;
import freemarker.template.TemplateException;
import javax.mail.MessagingException;
import java.io.IOException;

public interface IEmailService {
    String sendSimpleMail(EmailDetailsDto details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetailsDto details);

    String getEmailContent(User user) throws IOException, TemplateException;

    String getEmailContentRegister(User user) throws IOException, TemplateException;

    public void sendEmail(User user) throws IOException, TemplateException, MessagingException;

    public void sendEmailRegister(User user) throws IOException, TemplateException, MessagingException, freemarker.template.TemplateException;
}