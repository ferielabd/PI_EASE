package com.example.pi_ease.DTO;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CrCalculateLateFeeResponseDto {
    private BigDecimal totalLateFee;
    private BigDecimal lateFeeRate;
    private BigDecimal lateInterestTax;
    private Long lateDayCount;

    public BigDecimal getTotalLateFee() {
        return totalLateFee;
    }

    public void setTotalLateFee(BigDecimal totalLateFee) {
        this.totalLateFee = totalLateFee;
    }

    public BigDecimal getLateFeeRate() {
        return lateFeeRate;
    }

    public void setLateFeeRate(BigDecimal lateFeeRate) {
        this.lateFeeRate = lateFeeRate;
    }

    public BigDecimal getLateInterestTax() {
        return lateInterestTax;
    }

    public void setLateInterestTax(BigDecimal lateInterestTax) {
        this.lateInterestTax = lateInterestTax;
    }

    public Long getLateDayCount() {
        return lateDayCount;
    }

    public void setLateDayCount(Long lateDayCount) {
        this.lateDayCount = lateDayCount;
    }
}
