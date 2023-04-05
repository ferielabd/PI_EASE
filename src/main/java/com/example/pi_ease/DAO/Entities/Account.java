package com.example.pi_ease.DAO.Entities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idAccount;
    double solde;
    String rib;
    @Temporal(TemporalType.DATE)
    Date openDate;
    String state;

    @Enumerated(EnumType.STRING)
    TypeAccount typeAccount;
    //@ManyToMany(mappedBy = "accountList")
    //List<Transaction> transactionList;
    @OneToOne(mappedBy = "accounts")
    Checks checks;

    @ManyToMany
    List<User> userAcc;

}