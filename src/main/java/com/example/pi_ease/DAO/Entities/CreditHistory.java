package com.example.pi_ease.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreditHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_hisC")
    long Id;

     double income;

     @Enumerated(EnumType.STRING)
     CreditHistoryType creditHistoryType;
     int loanAmount;
    @OneToMany(mappedBy = "creditHistory",cascade = CascadeType.ALL)
    private List<Credit> Credits;

    public void setId(long id) {
        Id = id;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public CreditHistoryType getCreditHistoryType() {
        return creditHistoryType;
    }

    public void setCreditHistoryType(CreditHistoryType creditHistoryType) {
        this.creditHistoryType = creditHistoryType;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public List<Credit> getCredits() {
        return Credits;
    }

    public void setCredits(List<Credit> credits) {
        Credits = credits;
    }

    @Override
    public int getId() {
        return 0;
    }
}
