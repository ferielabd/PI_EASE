package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.User;

import java.util.List;

public interface IUserServices {
    User edit(User U);

    User add(User U);

    List<User> selectAll();
    User selectById(long idUser);
    void deleteById(long idUser);
    void delete (User U);
    List <User> addAll (List<User> list);
    void deleteAll(List<User> list);
    void active(long idUser);

}
