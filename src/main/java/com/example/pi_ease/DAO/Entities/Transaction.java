package com.example.pi_ease.DAO.Entities;

import java.util.List;
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
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTraction;
    float Amount;
    @Temporal(TemporalType.DATE)
    Date date_t;
    @Enumerated(EnumType.STRING)
    TypeTransaction typeTransaction;
    @ManyToMany
    List<Account> accountList;



}