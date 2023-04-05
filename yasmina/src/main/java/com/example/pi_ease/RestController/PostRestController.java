package com.example.pi_ease.RestControllers;
import com.example.pi_ease.DAO.Dto.PostResponse;
import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.PostRepository;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.Services.Classes.EmailServices;
import com.example.pi_ease.Services.Interface.IPostServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor

public class PostRestController {

    @Autowired
    IPostServices iPostServices ;
    private EmailServices emailServices ;
    private UserRepository userRepository ;
    private PostRepository postRepository;

    @GetMapping("/afficherPosts")
    public List<Post> afficher() {
        return iPostServices.selectAll();
    }

    @PostMapping("/ajouterPost")
    public Post ajouter(@RequestBody Post post) {
        List<String> badWords = Arrays.asList("aaa", "badword2", "badword3");
        // Check if the comment contains any bad words
        for (String word : badWords) {
            if (post.getText().toLowerCase().contains((CharSequence) word)) {
                //Ajoputer modifier supprimer findall w feha text et id
                // Increment the alert count for the user who made the post
                //User user = authController.getCurrentUser();
                User user = userRepository.findById(1L).get();
                System.out.println(user.getPhone());
                //int alertCount = user.getAlertCount();
                //user.setAlertCount(alertCount + 1);
                this.emailServices.sendSimpleEmail("mahnoud.mbshk@gmail.com", "badwords", "Attention!!!");
                return iPostServices.add(post);
            }
        }

        return iPostServices.add(post);
    }

    @PostMapping("/ajouterAllPost")
    public List<Post> addAll(@RequestBody List<Post> list){
        return iPostServices.addAll(list) ;}
    @GetMapping("/posts/popularite")
    public List<Post> trierParPopularite() {
        List<Post> posts = postRepository.findAll() ;  //Récupérez la liste de posts depuis la base de données ou une autre source
        return iPostServices.trierParPopularite(posts);
    }

    @PutMapping("/{postId}/archive-after-3-days")
    public ResponseEntity<String> archivePostAfter3Days(@PathVariable Long postId) {
        Post post = iPostServices.getPostById(postId);

        if (post == null) {
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        }

        iPostServices.archivePostAfter3Days(post);
        return new ResponseEntity<>("Post archived successfully", HttpStatus.OK);
    }
    @GetMapping("/afficherAllPost")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(iPostServices.getAllPosts());
    }
    @PutMapping("SharePost/{postId}/{id}")
    @ResponseBody

    public void SharePost(@PathVariable("postId") long postId,@PathVariable("id") long id) {
        iPostServices.SharePost(postId,id);
    }

    // http://localhost:8083/SpringMVC/Post/GetSharedPosts/1
    @GetMapping("/GetSharedPosts/{postId}")
    @ResponseBody
    public List<Post> GetSharedPosts(@PathVariable("postId") Long postId) {

        List<Post> Posts = iPostServices.GetSharedPosts(postId);
        return Posts;
    }
    @GetMapping("/afficherPostAvecId/{postId}")
    public Post afficherAvecId(@PathVariable long postId )
    {
        return iPostServices.selectById(postId);
    }
    @PutMapping("/modifierPost")
    public Post edit(@RequestBody Post post ){

        return iPostServices.edit(post);
    }

    @GetMapping("/byuser1/{name}")
    public List<Post> getPostsByUsername(@PathVariable("name") String userName) {
        return iPostServices.getPostsByUsername(userName);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long postId) {
//           try {
        boolean deleted = iPostServices.deletePost(postId);
        System.out.println(deleted);
        if (deleted) {
            return ResponseEntity.ok().build(); }
            /*if  le user connecté is admin ou c'est le post de user connecté */

        else {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
             /*Si user not admin ou n'est pas le post de user courant*/

        }
    }
    @PutMapping("/archive/{id}")
    public ResponseEntity<?> archivePost(@PathVariable("id") Long postId) {
        iPostServices.archivePost(postId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping ("/deletePostById/{postId}")
    public void deleteById(@PathVariable long postId){

        iPostServices.deleteByID(postId);
    }
    @DeleteMapping("/deleteAllPost")
    public void deleteAll(){
        iPostServices.deleteAll();}
    @GetMapping("/getforbidden")
    @ResponseBody
    public String getFobiddenWords() {
        return iPostServices.getForbiddenWords();
    }
    //http://localhost:8083/SpringMVC/Post/setforbidden
    @PostMapping("/setforbidden")
    @ResponseBody
    public void setForbiddenWords(@RequestBody String forbiddenWords) {
        if (forbiddenWords==null)
            forbiddenWords="";
        iPostServices.setForbiddenWords(forbiddenWords);
    }
    // http://localhost:8083/SpringMVC/Post/createPostForbidden/1

    @PutMapping("createPostForbidden/{postId}/{id}")
    @ResponseBody
    public void createPostForbidden(@PathVariable("postId") long postId ,@PathVariable("id") long id) {
        iPostServices.createPostForbidden(postId,id);
    }
}

