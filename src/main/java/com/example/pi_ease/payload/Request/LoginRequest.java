package com.example.pi_ease.payload.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

   /* @NotBlank
    private long id;

    @NotBlank

    private String mail;*/


/*
    @ApiModelProperty(value = "device Token", allowEmptyValue = false)
    private String deviceToken;

    @ApiModelProperty(value = "username", allowEmptyValue = false)
    @NotEmpty(message = "username should not be null or empty")
    private String username;

    @ApiModelProperty(value = "user Password", allowEmptyValue = false)
    @NotEmpty(message = "userPassword should not be null or empty")
    private String userPassword;
}
*/

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

