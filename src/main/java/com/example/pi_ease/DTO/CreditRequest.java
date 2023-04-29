package com.example.pi_ease.DTO;

import com.example.pi_ease.DAO.Entities.CreditHistory;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditRequest {

   // private CreditHistory creditHistory;
    private RepaymentCapacityDto repaymentCapacity;
    private BigDecimal loanAmount;

    // Constructeur prenant en compte tous les attributs de la demande de crédit
    public CreditRequest(  /*CreditHistory creditHistory,*/ RepaymentCapacityDto repaymentCapacity, BigDecimal loanAmount) {

       // this.creditHistory = creditHistory;
        this.repaymentCapacity = repaymentCapacity;
        this.loanAmount = loanAmount;
    }

    // Getters et setters pour chaque attribut




    public RepaymentCapacityDto getRepaymentCapacity() {
        return repaymentCapacity;
    }

    public void setRepaymentCapacity(RepaymentCapacityDto repaymentCapacity) {
        this.repaymentCapacity = repaymentCapacity;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }


}
