package com.example.pi_ease.Services.Interfaces;

import com.example.pi_ease.DAO.Entities.Account;
import com.example.pi_ease.DAO.Entities.Transaction;

import java.util.List;

public interface ITransactionService {
    Transaction add(Transaction t);
    Transaction edit(Transaction t);
    List<Transaction> selectAll();
    Transaction selectById(Integer idT);
    void deleteById(Integer idT);
    void delete(Transaction t);
    List<Transaction> addAll(List<Transaction> list);
    void deleteAll(List<Transaction> list);
}
