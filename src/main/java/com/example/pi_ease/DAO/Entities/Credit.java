package com.example.pi_ease.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    int IdC;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "demand_date")
    Date D_Date;


    @NotNull
    @Column(name = "interest_rate")
    float taux_interet;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "obtaining_Date")
    Date OB_Date;

    @NotNull
    @Min(1)
    @Column(name = "amount")
    float montant;
    @Column(name = "State")
    String etat;

    @NotNull
    @Column(name = "typeCredit")
    @Enumerated(EnumType.STRING)
    TypeCredit typeCredit;
    @NotNull
    String attachment;

    @NotNull
    String Description;

  @ManyToOne
    ActivitySector activitySector;
    @ManyToOne
    @JsonIgnore
    User userCredit;

    @OneToMany(mappedBy = "creditT",cascade = CascadeType.ALL)

    List<Tranche> trancheList;



}