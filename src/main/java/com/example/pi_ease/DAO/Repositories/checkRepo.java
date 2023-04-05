package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.Checks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface checkRepo extends JpaRepository<Checks,Integer> {
}
