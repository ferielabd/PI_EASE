package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.Message;

import java.util.List;

public interface IMessageServices {
    Message add(Message M);
    Message edit(Message M);
    List<Message> selectAll();
    Message selectById(int idMesg);
    void deleteByID (int idMeg);
    void delete (Message M);
    List<Message>addAll(List<Message> list);
    void deleteAll ( );
}
