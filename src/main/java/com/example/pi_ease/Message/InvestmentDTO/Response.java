package com.example.pi_ease.Message.InvestmentDTO;

import com.example.pi_ease.DAO.Entities.Investment;

import java.time.LocalDate;

public class Response {

    private long investmentId;

    private int amount;

    private LocalDate createdAt;

    // Constructor, getters and setters

    public Response() {
    }

    public Response(Investment investment) {
        this.investmentId = investment.getInvestmentId();
        this.amount = investment.getAmount();
        this.createdAt = investment.getCreatedAt();
    }

    public long getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(long investmentId) {
        this.investmentId = investmentId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}
