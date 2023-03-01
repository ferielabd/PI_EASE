package com.example.pi_ease.Services.Classes;
import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Repositories.CommentRepository;
import com.example.pi_ease.Services.Interface.ICommentServices;
import lombok.AllArgsConstructor ;
import org.springframework.stereotype.Service ;

import java.util.List ;
@Service
@AllArgsConstructor
public class CommentServices  implements ICommentServices {
    private CommentRepository commentRepository ;

    @Override
    public Comment add(Comment C) {
        return commentRepository.save(C) ;
    }

    @Override
    public Comment edit(Comment C) {
        return commentRepository.save(C) ;
    }
    @Override
    public List<Comment> selectAll() {
        return commentRepository.findAll() ;
    }

    @Override
    public Comment selectById(int idComm) {
        return commentRepository.findById(idComm).get() ;
    }

    @Override
    public void deleteByID(int idComm) {
        commentRepository.deleteById(idComm);

    }

    @Override
    public void delete(Comment C) {
        commentRepository.delete(C);

    }
    @Override
    public List<Comment> addAll(List<Comment> list) {
        return list ;
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();


    }




}
