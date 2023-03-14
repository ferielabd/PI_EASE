package com.example.pi_ease.DTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CrPayInstallmentResponseDto {
    private Long IdC;
    private BigDecimal paymentAmount;
    private LocalDate PaymentDate;
    private BigDecimal remainingPrincipal;
    private LocalDate dueDate;
}
