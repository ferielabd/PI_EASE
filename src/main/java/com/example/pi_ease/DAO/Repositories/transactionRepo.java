package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface transactionRepo extends JpaRepository<Transaction,Integer> {
}
