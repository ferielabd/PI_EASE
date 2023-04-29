package com.example.pi_ease.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RepaymentCapacityDto {
    private BigDecimal monthlyIncome;

    public RepaymentCapacityDto(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;

    }

    public RepaymentCapacityDto() {
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }


}
