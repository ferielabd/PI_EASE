package com.example.pi_ease;

import com.example.pi_ease.DAO.Config.twilioConfig;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import com.example.pi_ease.security.services.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.mail.javamail.JavaMailSender;



@SpringBootApplication
@EnableScheduling
  

public class PiEaseApplication extends SpringBootServletInitializer implements CommandLineRunner{
     //@Autowired
    //private twilioConfig twilioconfig;

    //@PostConstruct
    //public void initTwilio(){
      //  Twilio.init(twilioconfig.getAccountSid(),twilioconfig.getAuthToken());
    //}
    @Resource
    FilesStorageService storageService;
    @Autowired
    private JavaMailSender javaMailSender ;
    //	void sendEmail() {
//	SimpleMailMessage msg = new SimpleMailMessage();
//	msg.setTo("rihem.chagour@esprit.tn");
//	msg.setSubject("Testing from Spring Boot");
//	msg.setText("Hello World \n Spring Boot Email");
//	javaMailSender.send(msg);
//	}
//
    public static String uploadDirectory =
            System.getProperty("user.dir")+"/src/main/resources/static/uploads";
    public static void main(String[] args) {
        new File(uploadDirectory).mkdir();

        SpringApplication.run(PiEaseApplication.class, args);
    }

//	@Override
//    public void run(String... args) throws MessagingException, IOException {
//
//        System.out.println("Sending Email...");
//
//        sendEmail();
//
//
//        System.out.println("Done");
//
//    }


    @Override
    public void run(String... arg) throws Exception,MessagingException,
            IOException {
//		 sendEmail();
        storageService.deleteAll();
        storageService.init();
    }

}
