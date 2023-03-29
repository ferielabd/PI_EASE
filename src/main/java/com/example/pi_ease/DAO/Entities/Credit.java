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
    int IdC;

    @NotNull
    @Column(name = "demand_date")
    LocalDate D_Date;


    @NotNull
    @Column(name = "interest_rate")
    BigDecimal tauxInteret;


    @NotNull
    @Column(name = "obtaining_Date", precision = 19 ,scale =2 ,nullable = false)
    LocalDate OB_Date;

    @NotNull
    @Column(name = "due_date", precision = 19 ,scale =2 ,nullable = false)
    LocalDate dueDate;

    @NotNull
    @Min(1)
    @Column(name = "amount", precision = 19 ,scale =2 ,nullable = false)
    BigDecimal montant_demander;

    @Column(name="PRINCIPAL_TO_BE_PAID", precision = 19 ,scale =2 ,nullable = false)
     BigDecimal monPP;

    @Column(name="REMAINING_PRINCIPAL", precision = 19 ,scale =2 ,nullable = false)
     BigDecimal RestApay;
    @Enumerated(EnumType.STRING)
    @Column(name = "Credit_STATUS_TYPE",length=30,nullable = false)
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


    @Override
    public int getId() {
        return 0;
    }
}