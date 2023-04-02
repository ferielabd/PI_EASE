package com.example.pi_ease.DTO;

import lombok.Data;

@Data
public class SolvencyDto {
    private int creditScore;
    private int income;

    public SolvencyDto(int creditScore, int income) {
        this.creditScore = creditScore;
        this.income = income;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}
