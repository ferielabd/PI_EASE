package com.example.pi_ease.DAO.Entities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Claim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idClaim")
     int idClaim;
    @Temporal (TemporalType.DATE)
     Date DateClaim;
    @Temporal (TemporalType.DATE)
     Date DateTraite;

     Boolean TraiteClaim;
    @Enumerated(EnumType.STRING)
    TypeCL typeCL;

    String DescClaim;
    String AttachClaim;
  @ManyToOne(cascade=CascadeType.ALL)
    User userClaim;
}
