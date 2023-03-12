package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.Account;
import com.example.pi_ease.DAO.Entities.Transaction;
import com.example.pi_ease.DAO.Repositories.CompteIntrouvableException;
import com.example.pi_ease.DAO.Repositories.SoldeInsuffisantException;
import com.example.pi_ease.Services.Interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor

public class TransactionRest {
    private ITransactionService iTransactionService;

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

    @PostMapping("/virement")
    public ResponseEntity<String> effectuerVirement(@PathVariable Integer idCompteEmetteur, Integer idCompteBeneficiaire, float montant) {
        try {
            iTransactionService.effectuerVirement(idCompteEmetteur, idCompteBeneficiaire, montant);
            return ResponseEntity.ok("Virement effectué avec succès !");
        } catch (SoldeInsuffisantException | CompteIntrouvableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fonds insuffisants pour effectuer le virement.");
        }


    }

    @PostMapping("/versement")
    public ResponseEntity<String> effectuerVersement(@PathVariable Integer idCompteBeneficiaire, float montant) {

        iTransactionService.effectuerVersement(idCompteBeneficiaire, montant);
        return ResponseEntity.ok("Verement effectué avec succès !");

    }

    @PostMapping("/retrait")
    public ResponseEntity<String> effectuerRetrait(@PathVariable Integer idCompte, float montant) {
        try {
            iTransactionService.effectuerRetrait(idCompte, montant);
            return ResponseEntity.ok("Retrait effectué avec succès !");
        } catch (SoldeInsuffisantException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fonds insuffisants pour effectuer le retrait.");
        }


    }
}
