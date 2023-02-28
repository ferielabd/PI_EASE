package com.example.pi_ease.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pi_ease.DAO.Entities.Credit;

public interface CrediRepository extends JpaRepository<Credit,Integer> {
}
