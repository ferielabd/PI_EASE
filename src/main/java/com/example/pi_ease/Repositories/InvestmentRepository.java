package com.example.pi_ease.Repositories;

import com.example.pi_ease.DAO.Entities.Investment;
import com.example.pi_ease.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment,Long>  {
    List<Investment> findAllByUserInvestor(User user);
    List<Investment> findInvestmentByUserInvestorCin(long cin);
}
