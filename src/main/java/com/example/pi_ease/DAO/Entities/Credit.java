package com.example.pi_ease.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Credit  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_C")
    int idC;

    @Temporal(TemporalType.DATE)
    @Column(name = "demand_date")
    Date D_Date;
    @Column(name = "interest_rate")
    float taux_interet;
    @Temporal(TemporalType.DATE)
    @Column(name = "obtaining_Date")
    Date OB_Date;


    @Column(name = "amount")
    float montant;
    @Column(name = "State")
    String etat;

    @Column(name = "typeCredit")
    @Enumerated(EnumType.STRING)
    TypeCredit typeCredit;

    String attachment;
    String Description;

  @ManyToOne
    ActivitySector activitySector;
    @ManyToOne
    User userCredit;

    @OneToMany(mappedBy = "creditT")
    List<Tranche> trancheList;



}