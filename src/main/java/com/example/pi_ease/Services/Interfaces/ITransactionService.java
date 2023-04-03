package com.example.pi_ease.Services.Interfaces;

import com.example.pi_ease.DAO.Entities.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface ITransactionService {
    Transaction add(Transaction t);
    Transaction edit(Transaction t);
    List<Transaction> selectAll();
    Transaction selectById(Integer idT);

    List<Transaction> addAll(List<Transaction> list);

   String ajouterVirement(Transaction transaction);
    String ajouterRetrait(Transaction transaction);
    String ajouterVersement(Transaction transaction);

    double convertirEuroEnDinar(double montant);
}
