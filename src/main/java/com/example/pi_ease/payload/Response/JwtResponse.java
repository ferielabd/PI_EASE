package com.example.pi_ease.payload.Response;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Data
@Getter
public class JwtResponse {
    private String token;
    private long id;
    private String type="Bearer";
    private String cin;
    private String username;

    private Integer phone;
    private Date birthDate;

    private String mail;

    private List<String> roles;

    public JwtResponse(String accessToken,long id, String username,String mail, Integer phone,Date birthDate, List roles)
    {
        this.token=accessToken;
        this.id=id;
        this.username=username;
        this.mail=mail;
        this.phone=phone;
        this.birthDate=birthDate;
        this.roles=roles;
    }
    public String getAccessToken(){
        return token;
    }










}
