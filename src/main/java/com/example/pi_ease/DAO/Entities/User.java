package com.example.pi_ease.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User implements Serializable {

    @Id
    long cin;

    String First_name;

    String Last_name;

    String Adress;

    long phone;
    @Temporal(TemporalType.DATE)
    Date BirthDate;

    String mail;

    String pwd;

    @ManyToOne
    Role role;

    @ManyToMany(mappedBy = "receiver")
    List <Notification> notifications;

    @OneToMany (mappedBy = "user")
    List <Notification> emitter;

  @OneToMany(mappedBy = "userCredit")
    List<Credit> creditList;

    @OneToMany(mappedBy = "userClaim")
    List<Claim> claimList;

    @ManyToMany(mappedBy = "userAcc")
    List<Account>  accountList;

    @OneToMany
    List<Transaction> transactionE;

    @OneToMany
    List<Transaction> transactionR;
    /*@ManyToMany(mappedBy = "listUsersI")
    List<Project> listProjectI;*/
    /*@OneToMany(mappedBy = "userClient")
    List<Project> projectListC;*/

    //(mappedBy = "userClient")
    // @JsonManagedReference
    @OneToMany

    List<Project> projectList;

    // @JsonIgnore
    //@OneToMany(mappedBy = "userInvestor")
    //@JsonIgnore
    //List<Investment> investmentList;

    @OneToMany(mappedBy = "userInvestor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Investment> investmentList = new ArrayList<>();

   /* @OneToOne
    Portfolio portfolio;*/

    @ManyToOne
    Reaction reaction ;

    @ManyToOne
    Room room ;

    @OneToMany(mappedBy = "userComment")
    List<Comment> comt;
    @OneToMany(mappedBy = "userpost")
    List<Post> postList;
    @OneToMany
    List<Message> messageList;

}