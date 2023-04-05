package com.example.pi_ease.Exceptions;

import com.example.pi_ease.Services.Interfaces.BaseErrorMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
@Setter
public class GenBusinessException extends RuntimeException{

    private final BaseErrorMessage baseErrorMessage;

    public GenBusinessException( BaseErrorMessage baseErrorMessage) {
         this.baseErrorMessage = baseErrorMessage;
    }
}