package com.example.pi_ease.DTO;

import com.example.pi_ease.DAO.Entities.CreditHistoryType;
import com.example.pi_ease.DAO.Entities.CreditHistory;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.RestControllers.AuthController;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditScoringLibrary {

    @Autowired
    AuthController authController;
    User user = null;

    public double calculateCreditScore(CreditHistory creditHistory) {
        if(user == null)
            this.user = authController.getCurrentUser();

        double score = 0;
        // creditHistory.setIncome(user.getSalaire());
        // Calcule le score en fonction des attributs de la demande de crédit
        score += creditHistory.getIncome() / 1000;

        if (creditHistory.getCreditHistoryType() == CreditHistoryType.GOOD) {
            score += 50;
        } else if (creditHistory.getCreditHistoryType() == CreditHistoryType.FAIR) {
            score += 25;
        }

        if (creditHistory.getLoanAmount() < 10000) {
            score += 25;
        } else if (creditHistory.getLoanAmount() < 50000) {
            score += 50;
        } else {
            score += 75;
        }

        return score;
    }
}
