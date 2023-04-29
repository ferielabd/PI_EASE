package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DTO.CreditRequest;
import com.example.pi_ease.DTO.CreditScoringLibrary;
import com.example.pi_ease.DTO.RepaymentCapacityDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditDecisionService {

   private CreditScoringLibrary creditScoringLibrary;
    public CreditDecisionService(CreditScoringLibrary creditScoringLibrary) {
        this.creditScoringLibrary = creditScoringLibrary;
    }
    public boolean isCreditApproved(CreditRequest creditRequest) {
       /* // Vérification de la solvabilité de l'emprunteur
        if (!isSolvencyVerified(creditRequest.getCreditHistory())) {
            return false;
        }

        // Vérification de l'historique de crédit de l'emprunteur
        if (!isCreditHistoryVerified(creditRequest.getCreditHistory())) {
            return false;
        }*/

        // Vérification de la capacité de remboursement de l'emprunteur
        if (!isRepaymentCapacityVerified(creditRequest.getRepaymentCapacity())) {
            return false;
        }

        // Vérification de l'adéquation entre le montant du crédit demandé et la capacité de remboursement de l'emprunteur
        if (!isLoanAmountVerified(creditRequest.getLoanAmount(), creditRequest.getRepaymentCapacity())) {
            return false;
        }

        // Toutes les vérifications sont positives, on approuve le crédit
        return true;
    }

/*    private boolean isSolvencyVerified(CreditHistory creditHistory) {
        // Vérifie que l'emprunteur est solvable
        // Effectue une évaluation de crédit en utilisant la bibliothèque d'évaluation de crédit
        double creditScore = creditScoringLibrary.calculateCreditScore(creditHistory);

        return creditScore >= 700 && creditHistory.getIncome() >= 1500;
    }

   private boolean isCreditHistoryVerified(CreditHistory creditHistory) {
        // Vérifie l'historique de crédit de l'emprunteur
        return (creditHistory.getCreditHistoryType()==(CreditHistoryType.FAIR)||creditHistory.getCreditHistoryType()==CreditHistoryType.GOOD);

    }*/

    private boolean isRepaymentCapacityVerified(RepaymentCapacityDto repaymentCapacity) {
        // Vérifie la capacité de remboursement de l'emprunteur
        BigDecimal maxMonthlyPayment =  (repaymentCapacity.getMonthlyIncome().multiply(BigDecimal.valueOf(0.4)));
        BigDecimal monthlyPayment = repaymentCapacity.getMonthlyIncome().multiply(BigDecimal.valueOf(1/3));
        return monthlyPayment.intValue() <= maxMonthlyPayment.intValue();
    }


    private boolean isLoanAmountVerified(BigDecimal loanAmount, RepaymentCapacityDto repaymentCapacity) {
        // Vérifie que le montant du crédit demandé ne dépasse pas la capacité de remboursement de l'emprunteur
        BigDecimal maxLoanAmount = repaymentCapacity.getMonthlyIncome().multiply(BigDecimal.valueOf( 12 )).multiply(BigDecimal.valueOf( 0.3));
        return loanAmount.intValue() <= maxLoanAmount.intValue();
    }
}
