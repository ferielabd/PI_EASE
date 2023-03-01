package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.Room;

import java.util.List;

public interface ICommentServices {
    Comment add(Comment C);
    Comment edit(Comment C);
    List<Comment>selectAll();
    Comment selectById(int idComm);
    void deleteByID (int idComm);
    void delete (Comment C);
    List<Comment> addAll(List<Comment> list);
    void deleteAll ();
}


