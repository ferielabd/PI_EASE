package com.example.pi_ease.DAO.Entities;

import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTransaction;
    float montant;
    @Temporal(TemporalType.DATE)
    Date date_t;
    @Enumerated(EnumType.STRING)
    TypeTransaction typeTransaction;
    @ManyToOne
    private Account expediteur;
    @ManyToOne
    private Account destinataire;



}