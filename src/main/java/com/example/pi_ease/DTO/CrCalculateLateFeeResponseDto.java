package com.example.pi_ease.DTO;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CrCalculateLateFeeResponseDto {
    private BigDecimal totalLateFee;
    private BigDecimal lateFeeRate;
    private BigDecimal lateInterestTax;
    private Long lateDayCount;
}
