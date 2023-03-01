package com.example.pi_ease.RestController;


import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.Message;
import com.example.pi_ease.DAO.Entities.Reaction;
import com.example.pi_ease.DAO.Entities.Room;
import com.example.pi_ease.Services.Interface.IReactionServices;
import com.example.pi_ease.Services.Interface.IRoomServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReactionRestController {
    private IReactionServices iReactionServices ;
    @GetMapping("/afficherReaction")
    public List<Reaction> afficher() {
        return iReactionServices.selectAll();

    }
    @PostMapping("/ajouterReaction")
    public Reaction ajouter(@RequestBody Reaction reaction){
        return  iReactionServices.add(reaction);
    }

    @PostMapping("/ajouterAllReaction")
    public List<Reaction> addAll(@RequestBody List<Reaction> list){
        return iReactionServices.addAll(list) ;}
    @GetMapping("/afficherReactionAvecId/{idR}")
    public Reaction afficherAvecId(@PathVariable int idR )
    {

        return iReactionServices.selectById(idR);
    }
    @PutMapping("/modifierReaction")
    public Reaction edit(@RequestBody Reaction reaction ){

        return iReactionServices.edit(reaction);
    }
    @DeleteMapping ("/deleteReactionById")
    public void deleteById(@RequestParam int idR ){

        iReactionServices.deleteByID(idR); ;
    }
    @DeleteMapping ("/deleteReaction")
    public void deleteByObject(@RequestParam Reaction reaction ){

        iReactionServices.delete(reaction);
    }
    @DeleteMapping("/deleteAllReaction")
    public void deleteAll(){
        iReactionServices.deleteAll();}


}
