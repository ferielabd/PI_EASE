package com.example.pi_ease.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Credit extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_C")
    int Id;

    @Column(name="INSTALLMENT_COUNT",nullable = false)
     Integer installmentCount;

    @Column(name="PRINCIPAL_LOAN_AMOUNT", precision = 19 ,scale =2 ,nullable = false)
     BigDecimal principalLoanAmount;

    @Column(name="MONTHLY_INSTALLMENT_AMOUNT", precision = 19 ,scale =2 ,nullable = false)
     BigDecimal monthlyInstallmentAmount;

    @Column(name="INTEREST_TO_BE_PAID", precision = 19 ,scale =2 ,nullable = false)
     BigDecimal interestToBePaid;

    @Column(name="PRINCIPAL_TO_BE_PAID", precision = 19 ,scale =2 ,nullable = false)
     BigDecimal principalToBePaid;

    @Column(name="REMAINING_PRINCIPAL", precision = 19 ,scale =2 ,nullable = false)
     BigDecimal remainingPrincipal;

    @Column(name="DUE_DATE",nullable = false)
     LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name ="LOAN_STATUS_TYPE", length=30,nullable = false)
     CreditStatusType creditStatusType;

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
    @ManyToOne
    CreditHistory creditHistory;

    @Override
    public int getId() {
        return 0;
    }
}