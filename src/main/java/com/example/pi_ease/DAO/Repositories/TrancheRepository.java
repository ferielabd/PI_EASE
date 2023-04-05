package com.example.pi_ease.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pi_ease.DAO.Entities.Tranche;
import org.springframework.stereotype.Repository;

@Repository
public interface TrancheRepository extends JpaRepository<Tranche, Long> {
}
