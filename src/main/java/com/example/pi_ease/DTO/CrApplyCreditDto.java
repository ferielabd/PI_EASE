package com.example.pi_ease.DTO;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CrApplyCreditDto {

    private int nbT;
    private BigDecimal montant;
    private BigDecimal monthlySalary;
}
