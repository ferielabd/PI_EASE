package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Comment;
import com.example.pi_ease.DAO.Entities.Message;
import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServices implements IMessageServices {
    private MessageRepository messageRepository ;

    @Override
    public Message add(Message M) {
        return messageRepository.save(M) ;
    }

    @Override
    public Message edit(Message M) {

        return messageRepository.save(M) ;
    }
    @Override
    public List<Message> selectAll() {
        return messageRepository.findAll() ;
    }

    @Override
    public Message selectById(int idMesg) {
        return messageRepository.findById(idMesg).get() ;
    }

    @Override
    public void deleteByID(int idP) {
        messageRepository.deleteById(idP);

    }

    @Override
    public void delete(Message M) {
        messageRepository.delete(M);


    }
    @Override
    public List<Message> addAll(List<Message> list) {
        return list ;
    }

    @Override
    public void deleteAll() {

        messageRepository.deleteAll();;


        }

    }


