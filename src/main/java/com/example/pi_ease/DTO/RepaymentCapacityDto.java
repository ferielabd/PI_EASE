package com.example.pi_ease.DTO;

import lombok.Data;

@Data
public class RepaymentCapacityDto {
    private int monthlyIncome;
    private int monthlyExpenses;

    public RepaymentCapacityDto(int monthlyIncome, int monthlyExpenses) {
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenses = monthlyExpenses;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(int monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }
}
