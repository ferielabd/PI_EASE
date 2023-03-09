package com.example.pi_ease.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.pi_ease.DAO.Entities.ActivitySector;
import com.example.pi_ease.Services.Interfaces.IActivitySectorService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/ActivitySector")
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

    @PutMapping("/editerActivityS")
    public ActivitySector Modifier(@RequestBody ActivitySector activitySector){
        return iActivitySectorService.edit(activitySector);
    }

    @DeleteMapping("/supprimerActivityS/{id}")
    public void Supprimer(@PathVariable int id){
        iActivitySectorService.deleteById(id);
    }
}
