package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MessageRepository  extends JpaRepository<Message,Integer> {
}
