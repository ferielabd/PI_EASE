package com.example.pi_ease.DTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CrPayInstallmentResponseDto {
    private int IdCr;
    private BigDecimal paymentAmount;
    private LocalDate PaymentDate;
    private BigDecimal remainingPrincipal;
    private LocalDate dueDate;

    public int getIdCr() {
        return IdCr;
    }

    public void setIdCr(int idCrPay) {
        IdCr = idCrPay;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        PaymentDate = paymentDate;
    }

    public BigDecimal getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public void setRemainingPrincipal(BigDecimal remainingPrincipal) {
        this.remainingPrincipal = remainingPrincipal;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
