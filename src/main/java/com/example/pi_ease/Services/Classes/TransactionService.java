package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Transaction;
import com.example.pi_ease.DAO.Repositories.transactionRepo;
import com.example.pi_ease.Services.Interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@AllArgsConstructor

public class TransactionService implements ITransactionService {
    private transactionRepo transactionrepo;
    @Override
    public Transaction add(Transaction t) {
        return transactionrepo.save(t);
    }

    @Override
    public Transaction edit(Transaction t) {
        return transactionrepo.save(t);
    }

    @Override
    public List<Transaction> selectAll() {
        return transactionrepo.findAll();
    }

    @Override
    public Transaction selectById(Integer idT) {
        return transactionrepo.findById(idT).get();
    }

    @Override
    public void deleteById(Integer idT) {
          transactionrepo.deleteById(idT);

    }

    @Override
    public void delete(Transaction t) {
        transactionrepo.delete(t);

    }

    @Override
    public List<Transaction> addAll(List<Transaction> list) {
        return transactionrepo.saveAll(list);
    }

    @Override
    public void deleteAll(List<Transaction> list) {
        transactionrepo.deleteAll(list);

    }
}
