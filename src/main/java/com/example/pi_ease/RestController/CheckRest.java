package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.Account;
import com.example.pi_ease.DAO.Entities.Checks;
import com.example.pi_ease.DAO.UNIT.QRCodeGenerator;
import com.example.pi_ease.Services.Classes.CheckService;
import com.example.pi_ease.Services.Interfaces.IAccountService;
import com.example.pi_ease.Services.Interfaces.ICheckService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@AllArgsConstructor
public class CheckRest {
    private ICheckService iCheckService;
    @GetMapping("/afficherCheck")
    public List<Checks> afficher() {



        return  iCheckService.selectAll();
    }
    @PostMapping("/ajouterCheck")
    public Checks ajouter(@RequestBody Checks checks){return iCheckService.add(checks );

    }
    @PostMapping("/ajouterAllCheck")
    public List<Checks> addAll(@RequestBody List<Checks> list){return iCheckService.addAll(list );

    }
    @GetMapping("/afficherCheckById/{id}")
    public Checks afficherAvecId(@PathVariable int id){return iCheckService.selectById(id );

    }
    @PutMapping("/modifierCheck")
    public Checks edit(@RequestBody Checks checks){return iCheckService.edit(checks);

    }
    @DeleteMapping("/deleteCheckById")
    public void deleteById(@RequestParam int id){ iCheckService.deleteById(id);

    }
    @DeleteMapping("/deleteCheck")
    public void delete(@RequestParam Checks checks){ iCheckService.delete(checks);

    }
    @GetMapping
    public ResponseEntity<List<Checks>> getChecks() throws IOException, WriterException {
        List<Checks> checks = iCheckService.getChecks();
        if(checks.size()!=0){
            for (Checks checks1 : checks){
                QRCodeGenerator.generateQRCode(checks1);
            }
        }
        return ResponseEntity.ok(iCheckService.getChecks());
    }

}
