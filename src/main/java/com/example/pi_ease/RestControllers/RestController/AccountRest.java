package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.Account;
import com.example.pi_ease.Services.Interfaces.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
public class AccountRest {
    private IAccountService iAccountService;
    @GetMapping("/afficherCompte")
    public List<Account> afficher(){
        return iAccountService.selectAll();
    }
    @PostMapping("/ajouterCompte")
    public Account ajouter(@RequestBody Account account){return iAccountService.add(account );

    }
    @PostMapping("/ajouterAllCompte")
    public List<Account> addAll(@RequestBody List<Account> list){return iAccountService.addAll(list );

    }
    @GetMapping("/afficherAccountById/{id}")
    public Account afficherAvecId(@PathVariable int id){return iAccountService.selectById(id );

    }
    @PutMapping("/modifierCompte")
    public Account edit(@RequestBody Account account){return iAccountService.edit(account );

    }
    @DeleteMapping("/deleteCompteById")
    public void deleteById(@RequestParam int id){ iAccountService.deleteById(id);

    }
    @DeleteMapping("/deleteCompte")
    public void delete(@RequestParam Account account){ iAccountService.delete(account);

    }



}
