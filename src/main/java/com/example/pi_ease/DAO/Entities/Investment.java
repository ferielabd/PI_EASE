package com.example.pi_ease.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long investmentId;
    @NotNull
    int amount;

    @CreatedDate

    LocalDate createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    User userInvestor;

    @ManyToOne
    Project project;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    Portfolio portfolio;

    public void setPortfolio(Portfolio portfolio) {

        this.portfolio = portfolio;
        portfolio.getInvestments().add(this);

    }
}

