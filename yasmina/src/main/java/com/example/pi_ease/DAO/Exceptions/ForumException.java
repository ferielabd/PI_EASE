package com.example.pi_ease.DAO.Exceptions;

public class ForumException extends RuntimeException {
    public ForumException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }


}
