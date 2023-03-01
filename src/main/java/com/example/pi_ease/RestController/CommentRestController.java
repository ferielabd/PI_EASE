package com.example.pi_ease.RestController;
import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.Services.Interface.ICommentServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
public class CommentRestController {

        private ICommentServices iCommentServices;
        @GetMapping("/afficherComment")
        public List<Comment> afficher() {
            return iCommentServices.selectAll();

        }
        @PostMapping("/ajouterComment")
        public Comment ajouter(@RequestBody Comment comment){

            return  iCommentServices.add(comment);
        }
        @PostMapping("/ajouterAllComment")
        public List<Comment> addAll(@RequestBody List<Comment> list){
            return iCommentServices.addAll(list) ;}

       @GetMapping("/afficherCommentAvecId/{idComm}")
        public Comment afficherAvecId(@PathVariable int idComm){

            return iCommentServices.selectById(idComm);
        }
       @PutMapping("/modifierComment")
       public Comment edit(@RequestBody Comment comment ){

        return iCommentServices.edit(comment) ;
    }
    @DeleteMapping ("/deleteCommentById/{idComm}")
    public void deleteById(@PathVariable int idComm ){

        iCommentServices.deleteByID(idComm) ;
    }
    @DeleteMapping ("/deleteComment")
    public void deleteByObject(@RequestBody Comment comment ){

        iCommentServices.delete(comment);
    }
    @DeleteMapping("/deleteAllComment")
    public void deleteAll(){
        iCommentServices.deleteAll();}

    }


