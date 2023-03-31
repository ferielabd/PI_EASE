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
}
