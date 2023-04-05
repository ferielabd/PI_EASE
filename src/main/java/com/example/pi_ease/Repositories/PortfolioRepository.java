package com.example.pi_ease.Repositories;

import com.example.pi_ease.DAO.Entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio,Integer> {
    Portfolio findPortfolioByPort(int port);

}
