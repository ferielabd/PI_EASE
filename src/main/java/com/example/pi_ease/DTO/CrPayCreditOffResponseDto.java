package com.example.pi_ease.DTO;
import com.example.pi_ease.DAO.Entities.CreditStatusType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CrPayCreditOffResponseDto {
    private Long id;
    private Long customerId;
    private BigDecimal paidAmount;
    private BigDecimal remainingAmount;
    private CreditStatusType CreditStatusType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public com.example.pi_ease.DAO.Entities.CreditStatusType getCreditStatusType() {
        return CreditStatusType;
    }

    public void setCreditStatusType(com.example.pi_ease.DAO.Entities.CreditStatusType creditStatusType) {
        CreditStatusType = creditStatusType;
    }
}
