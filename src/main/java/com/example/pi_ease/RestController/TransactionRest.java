package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.Account;
import com.example.pi_ease.DAO.Entities.Transaction;
import com.example.pi_ease.Services.Interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor

public class TransactionRest {
    private ITransactionService iTransactionService;
    @GetMapping("/afficherTransaction")
    public List<Transaction> afficher(){
        return iTransactionService.selectAll();
    }
    @PostMapping("/ajouterTransaction")
    public Transaction ajouter(@RequestBody Transaction transaction){return iTransactionService.add(transaction );

    }
    @PostMapping("/ajouterAllTransaction")
    public List<Transaction> addAll(@RequestBody List<Transaction> list){return iTransactionService.addAll(list );

    }
    @GetMapping("/afficherTransactionById/{id}")
    public Transaction afficherAvecId(@PathVariable int id){return iTransactionService.selectById(id );

    }
    @PutMapping("/modifierTransaction")
    public Transaction edit(@RequestBody Transaction transaction){return iTransactionService.edit(transaction);

    }
    @DeleteMapping("/deleteTransactionById")
    public void deleteById(@RequestParam int id){ iTransactionService.deleteById(id);

    }
    @DeleteMapping("/deleteTransaction")
    public void delete(@RequestBody Transaction transaction){ iTransactionService.delete(transaction);

    }



}
