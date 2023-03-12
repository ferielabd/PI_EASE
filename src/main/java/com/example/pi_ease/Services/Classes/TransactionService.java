package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Account;
import com.example.pi_ease.DAO.Entities.Transaction;
import com.example.pi_ease.DAO.Repositories.CompteIntrouvableException;
import com.example.pi_ease.DAO.Repositories.SoldeInsuffisantException;
import com.example.pi_ease.DAO.Repositories.accountRepo;
import com.example.pi_ease.DAO.Repositories.transactionRepo;
import com.example.pi_ease.Services.Interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
@RestController
@AllArgsConstructor

public class TransactionService implements ITransactionService {
    private transactionRepo transactionrepo;
    private accountRepo accountrepo;

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
    public List<Transaction> addAll(List<Transaction> list) {
        return transactionrepo.saveAll(list);
    }
    Transaction transaction = new Transaction();




        public void effectuerVirement (Integer idCompteEmetteur, Integer idCompteBeneficiaire,float montant) throws
                SoldeInsuffisantException, CompteIntrouvableException {
        Account compteEmetteur = accountrepo.findById(idCompteEmetteur).orElseThrow(() -> new CompteIntrouvableException(idCompteEmetteur));
        Account compteBeneficiaire = accountrepo.findById(idCompteBeneficiaire).orElseThrow(() -> new CompteIntrouvableException(idCompteBeneficiaire));

        if (compteEmetteur.getSolde() < montant) {
            throw new SoldeInsuffisantException();
        }

        float nouveauSoldeEmetteur = compteEmetteur.getSolde()- montant;
        compteEmetteur.setSolde(nouveauSoldeEmetteur);
        accountrepo.save(compteEmetteur);

        float nouveauSoldeBeneficiaire = compteBeneficiaire.getSolde() + montant;
        compteBeneficiaire.setSolde(nouveauSoldeBeneficiaire);
        accountrepo.save(compteBeneficiaire);
        Transaction transaction = new Transaction();
        transaction.setDate_t(new Date());
        transaction.setMontant(montant);
        //transaction.set(compteEmetteur);
        //transaction.setCompteBeneficiaire(compteBeneficiaire);
        transactionrepo.save(transaction);

    }

    @Override
    public void effectuerVersement(Integer idCompteBeneficiaire, float montant){

            Account compteBeneficiaire = accountrepo.findById(idCompteBeneficiaire).get();
            float nouveauSoldeBeneficiaire = compteBeneficiaire.getSolde() + montant;
            compteBeneficiaire.setSolde(nouveauSoldeBeneficiaire);
            accountrepo.save(compteBeneficiaire);
            Transaction transaction = new Transaction();
            transaction.setDate_t(new Date());
            transaction.setMontant(montant);

            //transaction.setCompteBeneficiaire(compteBeneficiaire.getIdA());
            transactionrepo.save(transaction);
        }




    public void effectuerRetrait ( Integer idCompte,float montant)throws
            SoldeInsuffisantException  {

        Account compte = accountrepo.findById(idCompte).get();
        if (compte.getSolde() < montant) {
            throw new SoldeInsuffisantException();
        }
        float nouveauSoldeBeneficiaire = compte.getSolde() - montant;
        compte.setSolde(nouveauSoldeBeneficiaire);
        accountrepo.save(compte);
        Transaction transaction = new Transaction();
        transaction.setDate_t(new Date());
        transaction.setMontant(montant);
        //transaction.setCompteEmetteur(compteEmetteur);
        //transaction.setCompteBeneficiaire(compteBeneficiaire);
        transactionrepo.save(transaction);
    }
}


