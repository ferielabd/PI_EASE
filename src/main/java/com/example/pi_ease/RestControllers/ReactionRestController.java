package com.example.pi_ease.RestControllers;
import com.example.pi_ease.DAO.Entities.React;
import com.example.pi_ease.Services.Interface.IReactServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/Reacts/")
public class ReactionRestController {
    private IReactServices iReactionServices ;

    @PutMapping("/modifierReaction")
    public React edit(@RequestBody React react){

        return iReactionServices.edit(react);
    }

    @DeleteMapping("/deleteAllReaction")
    public void deleteAll(){
        iReactionServices.deleteAll();}
    @PostMapping("/ReactToPost/{reactionId}/{PostId}/{id}")
    @ResponseBody
    public void addReactToPost(@PathVariable("reactionId")int reaction,@PathVariable("PostId") Long PostId,@PathVariable("id") Long id) {
        iReactionServices.addReactToPost(reaction, PostId, id);
    }


    // http://localhost:8089/SpringMVC/React/DislikePost/1/1
    @DeleteMapping("/DislikePost/{idReaction}/{id}")
    public void DislikePost(@PathVariable("idReaction") int idReaction, @PathVariable("id") Long id) {
        iReactionServices.DislikePost(idReaction,id);
    }

    @GetMapping("/afficherReactionAvecId/{idReaction}")
    public React afficherAvecId(@PathVariable int idReaction )
    {

        return iReactionServices.selectById(idReaction);
    }

    // http://localhost:8089/SpringMVC/React/ReactToPost1/2/1/1
   @GetMapping("/LikeDislikePost/{reactionId}/{PostId}/{id}")
    public void LikeDislikePost(@PathVariable("reactionId")int reaction,@PathVariable("PostId") Long PostId,@PathVariable("id") Long id) {
        iReactionServices.LikeDislikePost(reaction, PostId, id);
    }


}
