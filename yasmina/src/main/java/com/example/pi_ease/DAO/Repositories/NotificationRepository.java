package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
