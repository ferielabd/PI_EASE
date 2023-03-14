package com.example.pi_ease.DTO;
import com.example.pi_ease.DAO.Entities.CreditStatusType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CrPayCreditOffResponseDto {
    private Long id;
    private Long UserId;
    private BigDecimal mon_pay;
    private BigDecimal RestApay;
    private CreditStatusType creditStatusType;
}
