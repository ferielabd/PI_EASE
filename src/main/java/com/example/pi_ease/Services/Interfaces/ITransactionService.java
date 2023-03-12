package com.example.pi_ease.Services.Interfaces;

import com.example.pi_ease.DAO.Entities.Account;
import com.example.pi_ease.DAO.Entities.Transaction;
import com.example.pi_ease.DAO.Repositories.CompteIntrouvableException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface ITransactionService {
    Transaction add(Transaction t);
    Transaction edit(Transaction t);
    List<Transaction> selectAll();
    Transaction selectById(Integer idT);

    List<Transaction> addAll(List<Transaction> list);

    void effectuerVirement(Integer idCompteEmetteur, Integer idCompteBeneficiaire, float montant) throws CompteIntrouvableException;

    void effectuerVersement(Integer idCompteBeneficiaire, float montant);

    void effectuerRetrait(Integer idCompte, float montant);
}
