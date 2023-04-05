package com.example.pi_ease.RestControllers;
import com.example.pi_ease.Dto.PostResponse;
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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts/")
public class PostRestController {


    private  IPostServices iPostServices ;
    private EmailServices emailServices ;
    private UserRepository userRepository ;
    private PostRepository postRepository;

    @PostMapping("/ajouterPost/{id}")
    public Post ajouter(@PathVariable Long id, @RequestBody Post post) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            post.setUserP(user);
            return postRepository.save(post);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " does not exist");
        }
    }

    @PostMapping("/ajouterAllPost")
    public List<Post> addAll(@RequestBody List<Post> list){
        return iPostServices.addAll(list) ;}

    @GetMapping("/popularite")
    public List<Post> trierParPopularite() {
        List<Post> posts = postRepository.findAll() ;  //Récupérez la liste de posts depuis la base de données ou une autre source
        return iPostServices.trierParPopularite(posts);
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

    @PutMapping("/FiltrerPost/{postId}/{id}")
    @ResponseBody
    public void FilterPostwithbadwords(@PathVariable("postId") long postId ,@PathVariable("id") long id) {
        iPostServices.FilterPostwithbadwords(postId,id);
    }

}

