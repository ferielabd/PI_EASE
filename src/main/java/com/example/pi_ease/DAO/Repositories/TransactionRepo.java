package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
}
