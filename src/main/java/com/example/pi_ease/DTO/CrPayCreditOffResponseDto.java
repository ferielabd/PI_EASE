package com.example.pi_ease.DTO;
import com.example.pi_ease.DAO.Entities.CreditStatusType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CrPayCreditOffResponseDto {
    private int id;
    private int UserId;
    private BigDecimal monPay;
    private BigDecimal RestApay;
    private CreditStatusType creditStatusType;
}
