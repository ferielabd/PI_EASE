package com.example.pi_ease.Exceptions;

import com.example.pi_ease.Services.Interfaces.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemNotFoundException extends GenBusinessException{

    public ItemNotFoundException(BaseErrorMessage message) {
        super(message);
    }
}