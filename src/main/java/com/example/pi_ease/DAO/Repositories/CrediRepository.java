package com.example.pi_ease.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pi_ease.DAO.Entities.Credit;
import org.springframework.stereotype.Repository;

@Repository
public interface CrediRepository extends JpaRepository<Credit,Long> {
}
