package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Dto.CommentDto;
import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.Room;
import com.example.pi_ease.DAO.Entities.User;

import java.util.List;

public interface ICommentServices {
    Comment add(Comment comment);
    Comment edit(Comment comment);
    List<Comment>selectAll();
    public String getForbiddenWords();
    public void setForbiddenWords(String words);
    public void FilterCommentwithbadwords(long idComm, long id);
    Comment selectById(long idComm);
    void deleteByID (long idComm);
    List<Comment> addAll(List<Comment> list);
    void deleteAll ();
    List<CommentDto> getAllCommentsForPost(Long postId);
    List<Comment> getAllCommentsForUser(String userName);
    Comment ajouterSousCommentaire(Long id ,Long idComm , Comment underComment);
    void awardCommentBadges(User user);
    List<Comment> afficherSousCommentaire(Long idComm);
}


