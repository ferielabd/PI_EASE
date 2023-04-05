package com.example.pi_ease.DAO.Entities;

import lombok.Data;

@Data

public class SmsRequest {
    private final String phoneNumber; // destination


    private final String message;
    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber= ..." + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
