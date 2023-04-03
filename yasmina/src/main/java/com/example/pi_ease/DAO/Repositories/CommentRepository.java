package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    int countByUser(User user);
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);

    List<Comment> findByUnderComment(Comment parent);
}
