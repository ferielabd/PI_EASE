package com.example.pi_ease.DTO;

import com.example.pi_ease.DAO.Entities.CreditHistory;
import lombok.Data;

@Data
public class CreditRequest {
    private SolvencyDto solvency;
    private CreditHistory creditHistory;
    private RepaymentCapacityDto repaymentCapacity;
    private double loanAmount;

    // Constructeur prenant en compte tous les attributs de la demande de crédit
    public CreditRequest(SolvencyDto solvency, CreditHistory creditHistory, RepaymentCapacityDto repaymentCapacity, double loanAmount) {
        this.solvency = solvency;
        this.creditHistory = creditHistory;
        this.repaymentCapacity = repaymentCapacity;
        this.loanAmount = loanAmount;
    }

    // Getters et setters pour chaque attribut
    public SolvencyDto getSolvency() {
        return solvency;
    }

    public void setSolvency(SolvencyDto solvency) {
        this.solvency = solvency;
    }

    public CreditHistory getCreditHistory() {
        return creditHistory;
    }

    public void setCreditHistory(CreditHistory creditHistory) {
        this.creditHistory = creditHistory;
    }

    public RepaymentCapacityDto getRepaymentCapacity() {
        return repaymentCapacity;
    }

    public void setRepaymentCapacity(RepaymentCapacityDto repaymentCapacity) {
        this.repaymentCapacity = repaymentCapacity;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }


}
