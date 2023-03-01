package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.Reaction;
import com.example.pi_ease.DAO.Repositories.PostRepository;
import com.example.pi_ease.DAO.Repositories.ReactionRepository;
import com.example.pi_ease.Services.Interface.IPostServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServices  implements IPostServices
{
    private PostRepository postRepository ;

    @Override
    public Post add(Post P) {
        return postRepository.save(P) ;
    }

    @Override
    public Post edit(Post P) {

        return postRepository.save(P) ;
    }
    @Override
    public List<Post> selectAll() {
        return postRepository.findAll() ;
    }

    @Override
    public Post selectById(int idP) {
        return postRepository.findById(idP).get() ;
    }

    @Override
    public void deleteByID(int idP) {
        postRepository.deleteById(idP);

    }

    @Override
    public void delete(Post P) {
        postRepository.delete(P);


    }
    @Override
    public List<Post> addAll(List<Post> list) {
        return list ;
    }

    @Override
    public void deleteAll() {
        postRepository.deleteAll();

    }



}
