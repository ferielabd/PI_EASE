package com.example.pi_ease.DAO.Repositories;

public class SoldeInsuffisantException extends RuntimeException{
    public SoldeInsuffisantException() {
        super("Solde insuffisant pour effectuer le virement.");
    }

    public SoldeInsuffisantException(String message) {
        super(message);
    }
}
