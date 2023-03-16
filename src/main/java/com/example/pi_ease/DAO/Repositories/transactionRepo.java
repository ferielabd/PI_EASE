package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface transactionRepo extends JpaRepository<Transaction,Integer> {

}
