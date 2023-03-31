package com.example.pi_ease.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tranche extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_T")
    int id;


    @Column(name="PAYMENT_AMOUNT", precision = 19 ,scale =2 ,nullable = false)
    private BigDecimal paymentAmount;

    @Column(name="PAYMENT_DATE",nullable = false)
    private LocalDate PaymentDate;

    @ManyToOne
    Credit creditT;


    @Override
    public int getId() {
        return 0;
    }
}
