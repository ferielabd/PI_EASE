package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.Room;

import java.util.List;

public interface IPostServices {
    Post add(Post P);
    Post edit(Post P);
    List<Post> selectAll();
    Post selectById(int idP);
    void deleteByID (int idP);
    void delete (Post P);
    List<Post>addAll(List<Post> list);
    void deleteAll ();

}
