package com.example.pi_ease.DAO.Request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private int phone;


    private Date birthDate;

    @NotBlank

    private String mail;

    private Set<String> roles;



}

