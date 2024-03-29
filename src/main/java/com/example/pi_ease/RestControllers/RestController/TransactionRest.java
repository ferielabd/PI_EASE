package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.Transaction;
import com.example.pi_ease.Services.Classes.EmailService;
import com.example.pi_ease.Services.Interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor

public class TransactionRest {
    private ITransactionService iTransactionService;
    private EmailService emailServices;

    @GetMapping("/afficherTransaction")
    public List<Transaction> afficher() {
        return iTransactionService.selectAll();
    }

    @PostMapping("/ajouterTransaction")
    public Transaction ajouter(@RequestBody Transaction transaction) {
        return iTransactionService.add(transaction);

    }

    @PostMapping("/ajouterAllTransaction")
    public List<Transaction> addAll(@RequestBody List<Transaction> list) {
        return iTransactionService.addAll(list);

    }

    @GetMapping("/afficherTransactionById/{id}")
    public Transaction afficherAvecId(@PathVariable int id) {
        return iTransactionService.selectById(id);

    }

    @PutMapping("/modifierTransaction")
    public Transaction edit(@RequestBody Transaction transaction) {
        return iTransactionService.edit(transaction);


    }

    @PostMapping
    @RequestMapping("addVir")
    public String ajouterVirement(@RequestBody Transaction transaction) {
        return iTransactionService.ajouterVirement(transaction);
    }




    @PostMapping
    @RequestMapping("addRet")
    public String ajouterRetrait(@RequestBody Transaction transaction) {
        return iTransactionService.ajouterRetrait(transaction);
    }

    @PostMapping
    @RequestMapping("addVers")
    public String ajouterVersement(@RequestBody Transaction transaction) {
        return iTransactionService.ajouterVersement(transaction);
    }
    @GetMapping("/{montant}")
    public double convertirEuroEnDinar(@PathVariable double montant) {
        return iTransactionService.convertirEuroEnDinar(montant);
    }
    @PostMapping("/message/{transactionId}")
    String sendEmailMessage(@PathVariable int transactionId) {
        this.emailServices.sendSimpleEmail( transactionId);
        return "Message sent";
    }
}
