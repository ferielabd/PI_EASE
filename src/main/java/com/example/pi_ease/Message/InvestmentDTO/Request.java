package com.example.pi_ease.Message.InvestmentDTO;

import com.example.pi_ease.DAO.Entities.Investment;
import com.example.pi_ease.DAO.Entities.User;

import javax.validation.constraints.NotNull;

public class Request {
    @NotNull
    private int amount;

    @NotNull
    private int projectId;





    // Constructor, getters and setters

    public Request() {
    }

    public Request(int amount, int projectId) {
        this.amount = amount;
        this.projectId = projectId;

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


    public Investment toEntity(User user) {
        Investment investment = new Investment();
        investment.setAmount(amount);
        investment.setUserInvestor(user);




        // You may need to fetch the Project entity by the projectId and set it on the investment entity
        return investment;
    }
}
