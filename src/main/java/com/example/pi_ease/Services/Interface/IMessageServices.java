package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.Message;
import com.example.pi_ease.DAO.Entities.User;

import java.util.List;

public interface IMessageServices {
    List<Message> findBySenderAndRecipient(User sender, User recipient);
    List<Message> findByRecipient(User recipient);
    Message save(Message message);
    void sendMessage(long senderUsername, long receiverUsername, String content);
}
