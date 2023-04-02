package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.CreditHistory;
import com.example.pi_ease.DTO.CreditRequest;
import com.example.pi_ease.DTO.RepaymentCapacityDto;
import com.example.pi_ease.DTO.SolvencyDto;
import org.springframework.stereotype.Service;

@Service
public class CreditDecisionService {

    public boolean isCreditApproved(CreditRequest creditRequest) {
        // Vérification de la solvabilité de l'emprunteur
        if (!isSolvencyVerified(creditRequest.getSolvency())) {
            return false;
        }

        // Vérification de l'historique de crédit de l'emprunteur
        if (!isCreditHistoryVerified(creditRequest.getCreditHistory())) {
            return false;
        }

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

    private boolean isSolvencyVerified(SolvencyDto solvency) {
        // Vérifie que l'emprunteur est solvable
        return solvency.getCreditScore() >= 600 && solvency.getIncome() >= 1500;
    }

    private boolean isCreditHistoryVerified(CreditHistory creditHistory) {
        // Vérifie l'historique de crédit de l'emprunteur
        return !creditHistory.hasDefaulted() && creditHistory.getNumCreditInquiries() <= 3;
    }

    private boolean isRepaymentCapacityVerified(RepaymentCapacityDto repaymentCapacity) {
        // Vérifie la capacité de remboursement de l'emprunteur
        int maxMonthlyPayment = (int) (repaymentCapacity.getMonthlyIncome() * 0.4);
        int monthlyPayment = repaymentCapacity.getMonthlyExpenses();
        return monthlyPayment <= maxMonthlyPayment;
    }


    private boolean isLoanAmountVerified(double loanAmount, RepaymentCapacityDto repaymentCapacity) {
        // Vérifie que le montant du crédit demandé ne dépasse pas la capacité de remboursement de l'emprunteur
        double maxLoanAmount = repaymentCapacity.getMonthlyIncome() * 12 * 0.3;
        return loanAmount <= maxLoanAmount;
    }
}
