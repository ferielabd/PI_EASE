package com.example.pi_ease.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Set;

 @Getter
 @Setter
public class SignupRequest {

    @Size(min = 3, max = 20)
    private String username ;
 
    @Size(max = 50)
    private String email;

    @Size(min = 6, max = 40)
    private String password;
    
    private Set<String> role;
   
   
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }

//	public String getFirstname() {
//		return firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getLastname() {
//		return lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
//	public String getBirthday() {
//		return birthday;
//	}
//
//	public void setBirthday(String birthday) {
//		this.birthday = birthday;
//	}
//
//	public String getCountry() {
//		return country;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}
//
//	public String getZipcode() {
//		return zipcode;
//	}
//
//	public void setZipcode(String zipcode) {
//		this.zipcode = zipcode;
//	}
//
//	public String getNomImageidentity() {
//		return nomImageidentity;
//	}
//
//	public void setNomImageidentity(String nomImageidentity) {
//		this.nomImageidentity = nomImageidentity;
//	}
//
//	public String getNomImageselfie() {
//		return nomImageselfie;
//	}

//	public void setNomImageselfie(String nomImageselfie) {
//		this.nomImageselfie = nomImageselfie;
//	}
    
    
    
    
}
