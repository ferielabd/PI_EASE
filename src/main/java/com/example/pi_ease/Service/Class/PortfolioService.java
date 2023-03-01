package com.example.pi_ease.Service.Class;

import com.example.pi_ease.DAO.Entities.Portfolio;
import com.example.pi_ease.Repositories.PortfolioRepository;
import com.example.pi_ease.Service.Interfaces.IPortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PortfolioService implements IPortfolioService {
    private PortfolioRepository portfolioRepository;

    @Override
    public Portfolio add(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    @Override
    public Portfolio edit(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    @Override
    public List<Portfolio> selectAll() {
        return portfolioRepository.findAll();
    }

    @Override
    public Portfolio selectByID(int idInv) {
        return portfolioRepository.findById(idInv).get();
    }

    @Override
    public void deleteById(int idInv) {
        portfolioRepository.deleteById(idInv);
    }

    @Override
    public void delete(Portfolio portfolio) {
        portfolioRepository.delete(portfolio);
    }

    @Override
    public List<Portfolio> addAll(List<Portfolio> listPortfolio) {
        return portfolioRepository.saveAll(listPortfolio);
    }

    @Override
    public void deleteAll(List<Portfolio> listPortfolio) {
        portfolioRepository.deleteAll(listPortfolio);


    }
}
