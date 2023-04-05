package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Message;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.MessageRepository;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.Services.Interface.IMessageServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageServices implements IMessageServices {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    public List<Message> findBySenderAndRecipient(User sender, User recipient) {
        return messageRepository.findBySenderAndRecipient(sender, recipient);
    }

    public List<Message> findByRecipient(User recipient) {
        return messageRepository.findByRecipient(recipient);
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }
    public void sendMessage(long senderUsername, long receiverUsername, String content) {
        User sender = userRepository.findById(senderUsername).get();
        User receiver = userRepository.findById(receiverUsername).get();
        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(receiver);
        message.setContent(content);
        messageRepository.save(message);
    }
}
