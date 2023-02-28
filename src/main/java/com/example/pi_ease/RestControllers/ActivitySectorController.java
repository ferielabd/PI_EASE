package com.example.pi_ease.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi_ease.DAO.Entities.ActivitySector;
import tn.esprit.pi_ease.Services.Interfaces.IActivitySectorService;

import java.util.List;
@RestController
@AllArgsConstructor
public class ActivitySectorController {

    private IActivitySectorService iActivitySectorService;
    @GetMapping("/afficherActivityS")
    public List<ActivitySector> afficher(){
        return iActivitySectorService.SelectAll();

    }
    @PostMapping("/ajouterActivityS")
    public ActivitySector ajouter(@RequestBody ActivitySector activitySector){
        return iActivitySectorService.add(activitySector);
    }

    @GetMapping("/afficherActivitySAvecId/{id}")
    public ActivitySector afficherAvecId(@PathVariable int id){
        return iActivitySectorService.selectById(id);
    }

    @PostMapping("/editerActivityS")
    public ActivitySector Modifier(@RequestBody ActivitySector activitySector){
        return iActivitySectorService.edit(activitySector);
    }

    @GetMapping("/supprimerActivityS/{id}")
    public void Supprimer(@PathVariable int id){
        iActivitySectorService.deleteById(id);
    }
}
