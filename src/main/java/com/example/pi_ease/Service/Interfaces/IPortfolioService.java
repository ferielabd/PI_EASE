package com.example.pi_ease.Service.Interfaces;

import com.example.pi_ease.DAO.Entities.Portfolio;

import java.util.List;

public interface IPortfolioService {
    Portfolio add(Portfolio portfolio);
    Portfolio edit(Portfolio portfolio);
    List<Portfolio> selectAll();
    Portfolio selectByID(int idInv);
    void deleteById(int idInv);
    void delete(Portfolio portfolio);
    List<Portfolio> addAll(List<Portfolio>listPortfolio);
    void deleteAll(List<Portfolio> listPortfolio);
    double getTotalInvestmentValue(int portfolioId);
    int getTotalPeopleLiftedOutOfPoverty(int portfolioId);

    int getTotalJobsCreated(int portfolioId);
    int getTotalWomenEntrepreneursSupported(int portfolioId);
}
