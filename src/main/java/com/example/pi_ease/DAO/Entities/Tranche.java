package com.example.pi_ease.DAO.Entities;


import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tranche extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    @Column(name="PAYMENT_AMOUNT", precision = 19 ,scale =2 ,nullable = false)
     BigDecimal paymentAmount;

    @Column(name="PAYMENT_DATE",nullable = false)
     LocalDate PaymentDate;

    @ManyToOne
    Credit creditT;

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        PaymentDate = paymentDate;
    }

    public Credit getCreditT() {
        return creditT;
    }

    public void setCreditT(Credit creditT) {
        this.creditT = creditT;
    }

    @Override
    public int getId() {
        return 0;
    }
}
