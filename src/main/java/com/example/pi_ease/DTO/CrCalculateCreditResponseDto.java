package com.example.pi_ease.DTO;

import lombok.Data;
import java.math.BigDecimal;
@Data
public class CrCalculateCreditResponseDto {
    private BigDecimal interestRate;
    private BigDecimal totalInterest;
    private BigDecimal monthlyInstallmentAmount;
    private BigDecimal totalPayment;
    private BigDecimal annualCostRate;
    private BigDecimal allocationFee;
}
