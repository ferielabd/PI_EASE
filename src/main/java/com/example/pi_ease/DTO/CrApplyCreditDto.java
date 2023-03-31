package com.example.pi_ease.DTO;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CrApplyCreditDto {

    private Integer installmentCount;
    private BigDecimal principalLoanAmount;
    private BigDecimal monthlySalary;
}
