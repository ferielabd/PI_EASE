package com.example.pi_ease.DAO.Repositories;


import com.example.pi_ease.DAO.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account,Integer> {
}