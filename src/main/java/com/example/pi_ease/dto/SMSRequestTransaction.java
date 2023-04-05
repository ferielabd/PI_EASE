package com.example.pi_ease.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class SMSRequestTransaction {
    @NotBlank
    private final String phoneNumber; // destination

    @NotBlank
    private final String message;

    public SMSRequestTransaction(@JsonProperty("phoneNumber") String phoneNumber,
                                 @JsonProperty("message") String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
}}
