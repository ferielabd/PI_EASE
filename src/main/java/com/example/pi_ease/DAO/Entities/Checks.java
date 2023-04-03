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
public class Checks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idC;
    int numC;
    @Enumerated(EnumType.STRING)
    TypeCheck typeCheck;
    @OneToOne
    Account accounts;
    Demande_C demande;
}