package com.example.pi_ease.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi_ease.DAO.Entities.ActivitySector;
import tn.esprit.pi_ease.DAO.Entities.Credit;
import tn.esprit.pi_ease.Services.Interfaces.IActivitySectorService;
import tn.esprit.pi_ease.Services.Interfaces.ICreditService;

import java.util.List;

@RestController
@AllArgsConstructor
public class CreditController {

    private ICreditService iCreditService;
    @GetMapping("/afficherCredit")
    public List<Credit> afficher(){
        return iCreditService.SelectAll();

    }
    @PostMapping("/ajouterCredit")
    public Credit ajouter(@RequestBody Credit credit){
        return iCreditService.add(credit);
    }

    @GetMapping("/afficherCreditAvecId/{id}")
    public Credit afficherAvecId(@PathVariable int id){
        return iCreditService.selectById(id);
    }

    @PostMapping("/editerCredit")
    public Credit Modifier(@RequestBody Credit credit){
        return iCreditService.edit(credit);
    }

    @GetMapping("/supprimerCredit/{id}")
    public void Supprimer(@PathVariable int id){
        iCreditService.deleteById(id);
    }
}
