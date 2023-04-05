package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.*;
import com.example.pi_ease.DAO.Repositories.accountRepo;
import com.example.pi_ease.DAO.Repositories.transactionRepo;
import com.example.pi_ease.Services.Interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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


    @Transactional
    public String ajouterVirement(Transaction transaction) {
        transaction.setDate_t(new Date());

        if (transaction.getTypeTransaction() == TypeTransaction.VIREMENT) {
            Account expediteur = accountrepo.findById(transaction.getExpediteur().getIdAccount()).get();

            if (expediteur.getTypeAccount() == TypeAccount.EPARGNE) {
                return "On n peut pas  faire le virement à partir d'epargne";
            } else if (expediteur.getTypeAccount() != TypeAccount.EPARGNE
                    && expediteur.getSolde() < transaction.getMontant() + 3) {
                return "On ne peut pas faire un virement: Solde insuff";
            }
            Account destinataire = accountrepo.findById(transaction.getDestinataire().getIdAccount()).get();
            transaction.setDestinataire(destinataire);
            transaction.setExpediteur(expediteur);

            transaction.getExpediteur().setSolde(transaction.getExpediteur().getSolde() - (transaction.getMontant() + 3));
            transaction.getDestinataire().setSolde(transaction.getDestinataire().getSolde() + transaction.getMontant());
            transactionrepo.save(transaction);
            return transaction.getTypeTransaction() + " de " + transaction.getMontant() + " DT de compte " + transaction.getExpediteur().getIdAccount() +
                    " vers le compte " + destinataire.getIdAccount() + " approuveé";
        }
        return "NOT VIR";
    }

    @Transactional
    public String ajouterVersement(Transaction transaction) {

        transaction.setDate_t(new Date());
        if (transaction.getTypeTransaction() == TypeTransaction.VERSEMENT) {
            Account destinataire = accountrepo.findById(transaction.getDestinataire().getIdAccount()).get();

            if (destinataire.getTypeAccount() != TypeAccount.EPARGNE) {
                transaction.setMontant(transaction.getMontant() - 2);
            }
            transaction.setDestinataire(destinataire);
            transaction.getDestinataire().setSolde(transaction.getDestinataire().getSolde() + transaction.getMontant());
            transactionrepo.save(transaction);
            return transaction.getTypeTransaction() + " de " + transaction.getMontant() + " DT vers compte " + transaction.getDestinataire().getIdAccount() +
                    " approuveé";
        }
        return "NOT VIR";
    }

    @Transactional
    public String ajouterRetrait(Transaction transaction) {
        transaction.setDate_t(new Date());

        if (transaction.getTypeTransaction() == TypeTransaction.RETRAIT) {
            Account expediteur = accountrepo.findById(transaction.getExpediteur().getIdAccount()).get();
            if (expediteur.getSolde() < transaction.getMontant() + 2) {
                return "SOLDE INSUFAISANT";
            }
            transaction.setExpediteur(expediteur);
            transaction.getExpediteur().setSolde(transaction.getExpediteur().getSolde() - (transaction.getMontant() + 2));
            transactionrepo.save(transaction);
            return transaction.getTypeTransaction() + " de " + transaction.getMontant() + " DT de compte " + transaction.getExpediteur().getIdAccount() +
                    " approuveé";
        }
        return "NOT VIR";
    }

    @Scheduled(fixedDelay = 30000 )
    public void GetAllTransactionByDate() {
        List<Transaction> transactionList = transactionrepo.findAll();

        for (Transaction t : transactionList) {
        }
        System.out.println("tttttt");

    }

    //public String sendTransactionEmails(Integer transactionId) {

        // Récupération de la transaction par ID
        //Transaction transaction = transactionrepo.findById(transactionId).get();

        // Récupération de l'utilisateur associé à la transaction
        //User user = transaction.getUser();

        // Envoi de l'e-mail à l'utilisateur
        //SimpleMailMessage message = new SimpleMailMessage();
       // message.setTo(user.getMail());
        //message.setSubject("Transaction réussie");
        //message.setText("Bonjour " + user.getFirst_name() + ",\n\nNous vous informons que votre transaction a été effectuée avec succès.\n\nCordialement,\nL'équipe de notre entreprise");

        //javaMailSender.send(message);
       // return "Emails sent successfully";


    //}
    private final double TAUX_DE_CHANGE = 3.5; // taux de change de l'euro au dinar

    public double convertirEuroEnDinar(double montantEuro) {
        return montantEuro * TAUX_DE_CHANGE;
    }
}


