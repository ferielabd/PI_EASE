package com.example.pi_ease.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tranche implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_T")
    int Id_T;

    @Column(name="nb_Tranche",nullable = false)
     int nbT;

    @Column(name="MONTHLY_AMOUNT", precision = 19 ,scale =2 ,nullable = false)
    float mon_pay;
    @Column(name="PAYMENT_DATE",nullable = false)
    @Temporal(TemporalType.DATE)
    Date pay_Date;

    @ManyToOne
    @JsonIgnore
    Credit creditT;
}
