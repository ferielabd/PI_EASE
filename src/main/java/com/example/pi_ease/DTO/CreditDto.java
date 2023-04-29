package com.example.pi_ease.DTO;

import com.example.pi_ease.DAO.Entities.CreditStatusType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreditDto {
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    Integer installmentCount;

    BigDecimal principalLoanAmount;

    BigDecimal monthlyInstallmentAmount;

    BigDecimal interestToBePaid;

    BigDecimal principalToBePaid;

    BigDecimal remainingPrincipal;

    LocalDate dueDate;


    CreditStatusType creditStatusType;

    String attachment;


    String Description;



    public void setInstallmentCount(Integer installmentCount) {
        this.installmentCount = installmentCount;
    }


    public void setPrincipalLoanAmount(BigDecimal principalLoanAmount) {
        this.principalLoanAmount = principalLoanAmount;
    }

    public void setMonthlyInstallmentAmount(BigDecimal monthlyInstallmentAmount) {
        this.monthlyInstallmentAmount = monthlyInstallmentAmount;
    }
    public void setInterestToBePaid(BigDecimal interestToBePaid) {
        this.interestToBePaid = interestToBePaid;
    }

    public void setPrincipalToBePaid(BigDecimal principalToBePaid) {
        this.principalToBePaid = principalToBePaid;
    }


    public void setRemainingPrincipal(BigDecimal remainingPrincipal) {
        this.remainingPrincipal = remainingPrincipal;
    }


    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCreditStatusType(CreditStatusType creditStatusType) {
        this.creditStatusType = creditStatusType;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    public void setDescription(String description) {
        Description = description;
    }
}
