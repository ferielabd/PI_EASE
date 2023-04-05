package com.example.pi_ease.DAO.Entities;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idP;
    @NotNull

    String Title;
    @NotNull

    String description;
    @Positive
    int totalInvestingAmount;
    @NotNull
    LocalDate startedAt;
    @NotNull
    LocalDate finishedAt;

    int peopleLiftedOutOfPoverty;
    int jobsCreated;
    int womenEntrepreneursSupported;

    @PositiveOrZero
    int currentInvestingAmount = 0;

    @PositiveOrZero
    int investorCount = 0;

    @CreatedDate
    LocalDateTime createdAt;

    Long netRevenueProject;
    Long costProject;
    double roiScore;


    @ManyToOne

    User userClient;


    //@JsonBackReference

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    List<Investment> investments;
    @OneToOne
    FileDb fileDB;



    public boolean isOpenForInvestment() {

        if (startedAt == null || finishedAt == null) {
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isBefore(startedAt) || currentDate.isAfter(finishedAt)) {
            return false;
        }

        // Check whether the product has not exceeded its total investing amount
        if (currentInvestingAmount >= totalInvestingAmount) {
            return false;
        }

        return true;
    }

    public int getRemainingInvestmentCapacity() {
        return totalInvestingAmount - currentInvestingAmount;
    }

}
