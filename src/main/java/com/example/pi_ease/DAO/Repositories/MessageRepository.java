package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Message;
import com.example.pi_ease.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findBySenderAndRecipient(User sender, User recipient);
    List<Message> findByRecipient(User recipient);
}
