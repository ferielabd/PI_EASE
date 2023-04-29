package com.example.pi_ease.DTO;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CrApplyCreditDto {

    private Integer installmentCount;
    private BigDecimal principalLoanAmount;
    private BigDecimal monthlySalary;
    private String attachment;
    private String description;
    private String nameSA;

    public String getNameSA() {
        return nameSA;
    }

    public void setNameSA(String nameSA) {
        this.nameSA = nameSA;
    }

    public String getAttachment() {
        return attachment;
    }

    public String getDescription() {
        return description;
    }

    public Integer getInstallmentCount() {
        return installmentCount;
    }


    public BigDecimal getPrincipalLoanAmount() {
        return principalLoanAmount;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

}
