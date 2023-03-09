package com.example.pi_ease.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.pi_ease.DAO.Entities.ActivitySector;
import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.Services.Interfaces.IActivitySectorService;
import com.example.pi_ease.Services.Interfaces.ICreditService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Credit")
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

    @PutMapping("/editerCredit")
    public Credit Modifier(@RequestBody Credit credit){
        return iCreditService.edit(credit);
    }

    @DeleteMapping("/supprimerCredit/{id}")
    public void Supprimer(@PathVariable int id){
        iCreditService.deleteById(id);
    }
}
