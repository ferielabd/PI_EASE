package com.example.pi_ease.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditHistory extends JpaRepository<CreditHistory,Long> {
}
