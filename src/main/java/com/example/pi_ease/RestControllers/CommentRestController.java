package com.example.pi_ease.RestControllers;
import com.example.pi_ease.Dto.CommentDto;
import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.CommentRepository;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.Services.Classes.EmailServices;
import com.example.pi_ease.Services.Interface.ICommentServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/Comments/")
public class CommentRestController {

   private ICommentServices iCommentServices ;
    private EmailServices emailServices;
    private UserRepository userRepository;
    private CommentRepository commentRepository;


    @GetMapping("/afficherAllComment")
    public List<Comment> afficher() {
        return iCommentServices.selectAll();

    }

    @PostMapping("/ajouterComment")
    public Comment ajouter(@RequestBody Comment comment) {
        return  iCommentServices.add(comment);
    }

    @GetMapping("/getforbidden")
    @ResponseBody
    public String getFobiddenWords() {
        return iCommentServices.getForbiddenWords();
    }
    //http://localhost:8083/SpringMVC/Post/setforbidden
    @PostMapping("/setforbidden")
    @ResponseBody
    public void setForbiddenWords(@RequestBody String forbiddenWords) {
        if (forbiddenWords==null)
            forbiddenWords="";
        iCommentServices.setForbiddenWords(forbiddenWords);
    }
    // http://localhost:8083/SpringMVC/Post/createPostForbidden/1

    @PutMapping("/FiltrerComment/{idComm}/{id}")
    @ResponseBody
    public void  FilterCommentwithbadwords(@PathVariable("idComm") long idComm ,@PathVariable("id") long id) {
        iCommentServices.FilterCommentwithbadwords(idComm,id);
    }


    @PostMapping("/ajouterSousCommentaire/{id}/{idComm}")
    public Comment ajouterSousCommentaire(@PathVariable Long id ,@PathVariable Long idComm ,@RequestBody Comment underComment){
        String message="";
            System.out.println("...yaaa"+idComm);

            message="Souscomment";
            return iCommentServices.ajouterSousCommentaire(id,idComm,underComment);
           }

    @GetMapping("/afficherSousCommentaire/{idComm}")
    public List<Comment> afficherSousCommentaire(@PathVariable Long idComm){

        return iCommentServices.afficherSousCommentaire(idComm);
    }

       @GetMapping("/afficherCommentAvecId/{idComm}")
        public Comment afficherAvecId(@PathVariable long idComm){

            return iCommentServices.selectById(idComm);
        }
    @PostMapping("/message")
    String sendEmailMessage() {
        this.emailServices.sendSimpleEmail("rihemchagour5@gmail.com", "etat de reclamation", "votre reclamation est traitée");
        return "Message sent";
    }
       @PutMapping("/modifierComment")
       public Comment edit(@RequestBody Comment comment ){

        return iCommentServices.edit(comment) ;
    }


    @DeleteMapping ("/deleteCommentById/{idComm}")
    public void deleteById(@PathVariable long idComm ){

        iCommentServices.deleteByID(idComm) ;
    }

    @DeleteMapping("/deleteAllComment")
    public void deleteAll(){
        iCommentServices.deleteAll();}
    @PutMapping("/awardComment")
    public void awardCommentBadges(@RequestBody User user){
        iCommentServices.awardCommentBadges(user);}
    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(iCommentServices.getAllCommentsForPost(postId));
    }
    /*@GetMapping("/by-post/{postId}")
    public List<Comment> getAllCommentsForPost(@PathVariable Long postId) {

        return iCommentServices.getAllCommentsForPost(postId);
    }*/

    @GetMapping("/by-user/{userName}")
    public List<Comment> getAllCommentsForUser(@PathVariable String userName) {
        return iCommentServices.getAllCommentsForUser(userName);
    }



    }


