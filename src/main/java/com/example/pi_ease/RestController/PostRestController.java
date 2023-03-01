package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.Message;
import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.Reaction;
import com.example.pi_ease.Services.Interface.IPostServices;
import com.example.pi_ease.Services.Interface.IReactionServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PostRestController {
    private IPostServices iPostServices ;
    @GetMapping("/afficherPost")
    public List<Post> afficher() {
        return iPostServices.selectAll();

    }
    @PostMapping("/ajouterPost")
    public Post ajouter(@RequestBody Post post){
        return  iPostServices.add(post);
    }

    @PostMapping("/ajouterAllPost")
    public List<Post> addAll(@RequestBody List<Post> list){
        return iPostServices.addAll(list) ;}
    @GetMapping("/afficherPostAvecId/{idP}")
    public Post afficherAvecId(@PathVariable int idP )
    {

        return iPostServices.selectById(idP);
    }
    @PutMapping("/modifierPost")
    public Post edit(@RequestBody Post post ){

        return iPostServices.edit(post);
    }
    @DeleteMapping ("/deletePostById")
    public void deleteById(@RequestParam int idP ){

        iPostServices.deleteByID(idP); ;
    }
    @DeleteMapping ("/deletePost")
    public void deleteByObject(@RequestParam Post post ){

        iPostServices.delete(post);
    }
    @DeleteMapping("/deleteAllPost")
    public void deleteAll(){
        iPostServices.deleteAll();}

}
