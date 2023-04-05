package com.example.pi_ease.DAO.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    long id;

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
    User userCredit;

    @OneToMany(mappedBy = "creditT",cascade = CascadeType.ALL)

    List<Tranche> trancheList;
    @ManyToOne
    CreditHistory creditHistory;

    public void setId(int id) {
        id = id;
    }

    public Integer getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(Integer installmentCount) {
        this.installmentCount = installmentCount;
    }

    public BigDecimal getPrincipalLoanAmount() {
        return principalLoanAmount;
    }

    public void setPrincipalLoanAmount(BigDecimal principalLoanAmount) {
        this.principalLoanAmount = principalLoanAmount;
    }

    public BigDecimal getMonthlyInstallmentAmount() {
        return monthlyInstallmentAmount;
    }

    public void setMonthlyInstallmentAmount(BigDecimal monthlyInstallmentAmount) {
        this.monthlyInstallmentAmount = monthlyInstallmentAmount;
    }

    public BigDecimal getInterestToBePaid() {
        return interestToBePaid;
    }

    public void setInterestToBePaid(BigDecimal interestToBePaid) {
        this.interestToBePaid = interestToBePaid;
    }

    public BigDecimal getPrincipalToBePaid() {
        return principalToBePaid;
    }

    public void setPrincipalToBePaid(BigDecimal principalToBePaid) {
        this.principalToBePaid = principalToBePaid;
    }

    public BigDecimal getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public void setRemainingPrincipal(BigDecimal remainingPrincipal) {
        this.remainingPrincipal = remainingPrincipal;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public CreditStatusType getCreditStatusType() {
        return creditStatusType;
    }

    public void setCreditStatusType(CreditStatusType creditStatusType) {
        this.creditStatusType = creditStatusType;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public ActivitySector getActivitySector() {
        return activitySector;
    }

    public void setActivitySector(ActivitySector activitySector) {
        this.activitySector = activitySector;
    }

    public User getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(User userCredit) {
        this.userCredit = userCredit;
    }

    public List<Tranche> getTrancheList() {
        return trancheList;
    }

    public void setTrancheList(List<Tranche> trancheList) {
        this.trancheList = trancheList;
    }

    public CreditHistory getCreditHistory() {
        return creditHistory;
    }

    public void setCreditHistory(CreditHistory creditHistory) {
        this.creditHistory = creditHistory;
    }

    @Override
    public int getId() {
        return 0;
    }
}