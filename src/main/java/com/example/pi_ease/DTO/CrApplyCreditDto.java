package com.example.pi_ease.DTO;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CrApplyCreditDto {

    private Integer installmentCount;
    private BigDecimal principalLoanAmount;
    private BigDecimal monthlySalary;

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

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
}
