package com.example.pi_ease.DTO;

import lombok.Data;
import java.math.BigDecimal;
@Data
public class CrCalculateCreditResponseDto {
    private BigDecimal interestRate;
    private BigDecimal totalInterest;
    private BigDecimal monthlyInstallmentAmount;
    private BigDecimal totalPayment;
    private BigDecimal annualCostRate;
    private BigDecimal allocationFee;

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getMonthlyInstallmentAmount() {
        return monthlyInstallmentAmount;
    }

    public void setMonthlyInstallmentAmount(BigDecimal monthlyInstallmentAmount) {
        this.monthlyInstallmentAmount = monthlyInstallmentAmount;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public BigDecimal getAnnualCostRate() {
        return annualCostRate;
    }

    public void setAnnualCostRate(BigDecimal annualCostRate) {
        this.annualCostRate = annualCostRate;
    }

    public BigDecimal getAllocationFee() {
        return allocationFee;
    }

    public void setAllocationFee(BigDecimal allocationFee) {
        this.allocationFee = allocationFee;
    }
}
