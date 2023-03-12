package com.example.pi_ease.DAO.Repositories;

public class CompteIntrouvableException extends Exception{
    public CompteIntrouvableException(Integer id)
    {
        super("Compte introuvable pour effectuer la transaction.");
    }

    public CompteIntrouvableException(String message) {

        super(message);
    }
}
