package com.example.pi_ease.RestController;

import com.example.pi_ease.Service.Class.EmailCService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/Email")
public class EmailCRestController {
    private EmailCService emailService;
    @PostMapping("/message")
    String sendEmailMessage() {
        this.emailService.sendSimpleMessage("lenders.mfi123@gmail.com", "etat de reclamation", "votre reclamation est traitée");
        return "Message sent";
    }
}
