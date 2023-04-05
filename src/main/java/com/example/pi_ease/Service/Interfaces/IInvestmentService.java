package com.example.pi_ease.Service.Interfaces;

import com.example.pi_ease.DAO.Entities.Investment;
import com.example.pi_ease.Message.InvestmentDTO.Request;
import com.example.pi_ease.Message.InvestmentDTO.Response;

import java.util.List;

public interface IInvestmentService {
    List<Response> getInvestments(long cin);

    Response createInvestment(int projectId, Request investmentDTO, long cin,int portfolioId);

    Investment add(Investment investment);
    Investment edit(Investment investment);
    List<Investment> selectAll();
    Investment selectByID(Long investmentId);
    void deleteById(Long investmentId);
    void delete(Investment investment);
    List<Investment> addAll(List<Investment>listInvestment);
    void deleteAll(List<Investment> listInvestment);

}
