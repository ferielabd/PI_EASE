package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.React;
import com.example.pi_ease.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserP(User user);


    int countByUserP(User user);
    List<Post> findAllByDeletedTrue();
    List<Post> findAllByUserP(User user);

    List<Post> findByReact(React react);
}
