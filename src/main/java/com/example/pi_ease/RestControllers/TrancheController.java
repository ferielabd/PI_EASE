package com.example.pi_ease.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.pi_ease.DAO.Entities.ActivitySector;
import com.example.pi_ease.DAO.Entities.Tranche;
import com.example.pi_ease.Services.Interfaces.IActivitySectorService;
import com.example.pi_ease.Services.Interfaces.ITrancheService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Tranche")
public class TrancheController {
    private ITrancheService iTrancheService;
    @GetMapping("/afficherTranche")
    public List<Tranche> afficher(){
        return iTrancheService.SelectAll();

    }
    @PostMapping("/ajouterTranche")
    public Tranche ajouter(@RequestBody Tranche tranche){
        return iTrancheService.add(tranche);
    }

    @GetMapping("/afficherTrancheAvecId/{id}")
    public Tranche afficherAvecId(@PathVariable int id){
        return iTrancheService.selectById(id);
    }

    @PutMapping("/editerTranche")
    public Tranche Modifier(@RequestBody Tranche tranche){
        return iTrancheService.edit(tranche);
    }

    @DeleteMapping("/supprimerTranche/{id}")
    public void Supprimer(@PathVariable int id){
        iTrancheService.deleteById(id);
    }
}
