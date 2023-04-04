package com.example.pi_ease.DAO.Repositories;

import com.example.pi_ease.DAO.Entities.CreditHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditHistoryRepository extends JpaRepository<CreditHistory,Long> {
}
