package com.example.pi_ease.Exceptions;

public class PostNotFoundException extends RuntimeException  {
    public PostNotFoundException(String message) {
        super(message);
    }

}
