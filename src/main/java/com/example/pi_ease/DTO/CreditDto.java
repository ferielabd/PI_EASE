package com.example.pi_ease.DTO;

import com.example.pi_ease.DAO.Entities.CreditStatusType;
import lombok.Data;


import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreditDto {

    long Id;

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

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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
}
