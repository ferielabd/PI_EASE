package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditRepo extends JpaRepository<Credit,Long> {
    Credit findByIdCAndUserCredit(long idc, User user);


}
