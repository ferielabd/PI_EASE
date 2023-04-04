package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DAO.Entities.CreditStatusType;
import com.example.pi_ease.DTO.CrApplyCreditDto;
import com.example.pi_ease.Exceptions.CreditErrorMessage;
import com.example.pi_ease.Exceptions.IllegalFieldException;
import com.example.pi_ease.Exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditValidationService {

    public void controlIsParameterNotNull(Integer installmentCount, BigDecimal principalLoanAmount) {

        boolean hasNull = installmentCount == null || principalLoanAmount == null;

        if (hasNull) {
            throw new IllegalFieldException(CreditErrorMessage.PARAMETER_CANNOT_BE_NULL);
        }
    }

    public void controlIsParameterNotNull(CrApplyCreditDto loaApplyLoanDto) {

        boolean hasNull = loaApplyLoanDto.getInstallmentCount() == null ||
                loaApplyLoanDto.getPrincipalLoanAmount() == null ||
                loaApplyLoanDto.getMonthlySalary() == null;

        if (hasNull) {
            throw new IllegalFieldException(CreditErrorMessage.PARAMETER_CANNOT_BE_NULL);
        }
    }

    public void controlIsInterestRateNotNegative(BigDecimal interestRate) {

        if (interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalFieldException(CreditErrorMessage.INTEREST_RATE_CANNOT_BE_NEGATIVE);
        }
    }


    public void controlIsInstallmentAmountPositive(BigDecimal monthlyInstallmentAmount) {

        if (monthlyInstallmentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalFieldException(CreditErrorMessage.INSTALLMENT_AMOUNT_MUST_BE_POSITIVE);
        }
    }

    public void controlIsTotalPaymentPositive(BigDecimal totalPayment) {

        if (totalPayment.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalFieldException(CreditErrorMessage.TOTAL_PAYMENT_MUST_BE_POSITIVE);
        }
    }

    public void controlIsLateFeeRateNotNegative(BigDecimal lateFeeRate) {

        if (lateFeeRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalFieldException(CreditErrorMessage.LATE_FEE_RATE_CANNOT_BE_NEGATIVE);
        }
    }

    public void controlIsTotalLateFeePositive(BigDecimal totalLateFee) {

        if (totalLateFee.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalFieldException(CreditErrorMessage.TOTAL_LATE_FEE_MUST_BE_POSITIVE);
        }
    }

    public void controlIsLateInterestTaxNotNegative(BigDecimal lateInterestTax) {

        if (lateInterestTax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalFieldException(CreditErrorMessage.LATE_INTEREST_TAX_CANNOT_BE_NEGATIVE);
        }
    }

    public void controlIsLoanAmountNotGreaterThanMaxLoanAmount(BigDecimal principalLoanAmount, BigDecimal maxLoanAmount) {

        if (principalLoanAmount.compareTo(maxLoanAmount) > 0) {

            CreditErrorMessage loaErrorMessage = CreditErrorMessage.LOAN_AMOUNT_CANNOT_BE_GREATER_THAN_MAX_AMOUNT;
            loaErrorMessage.setDetailMessage(String.valueOf(maxLoanAmount));

            throw new IllegalFieldException(loaErrorMessage);
        }
    }


    public void controlIsMonthlyInstallmentAmountPositive(BigDecimal monthlyInstallmentAmount) {

        if (monthlyInstallmentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalFieldException(CreditErrorMessage.MONTHLY_INSTALLMENT_AMOUNT_MUST_BE_POSITIVE);
        }
    }

    public void controlIsInterestAmountNotNegative(BigDecimal interestAmount) {

        if (interestAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalFieldException(CreditErrorMessage.INTEREST_AMOUNT_CANNOT_BE_NEGATIVE);
        }
    }

    public void controlIsPrincipalLoanAmountPositive(BigDecimal principalLoanAmount) {

        if (principalLoanAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalFieldException(CreditErrorMessage.PRINCIPAL_lOAN_AMOUNT_MUST_BE_POSITIVE);
        }
    }

    public Long controlIsLoanDueDatePast(LocalDate dueDate) {

        LocalDate now = LocalDate.now();
        long lateDayCount = ChronoUnit.DAYS.between(dueDate, now);

        if (lateDayCount < 1) {

            CreditErrorMessage loaErrorMessage = CreditErrorMessage.DUE_DATE_HAS_NOT_PASSED_YET;
            loaErrorMessage.setDetailMessage(String.valueOf(dueDate));

            throw new IllegalFieldException(loaErrorMessage);
        }
        return lateDayCount;
    }

    public void controlIsRemainingPrincipalNotNegative(BigDecimal remainingPrincipal) {
        if (remainingPrincipal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalFieldException(CreditErrorMessage.REMAINING_PRINCIPAL_MUST_BE_POSITIVE);
        }
    }

    public void controlIsTaxRateNotNegative(BigDecimal taxRate) {
        if (taxRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalFieldException(CreditErrorMessage.TAX_RATE_CANNOT_BE_NEGATIVE);
        }
    }

    public void controlIsInstallmentCountNotGreaterThanInstallmentCountLimit(int installmentCount, int installmentCountLimit) {
        if (installmentCount > installmentCountLimit) {

            CreditErrorMessage loaErrorMessage = CreditErrorMessage.INSTALLMENT_COUNT_CANNOT_BE_LARGER_THAN_LIMIT;
            loaErrorMessage.setDetailMessage(String.valueOf(installmentCountLimit));

            throw new IllegalFieldException(loaErrorMessage);
        }
    }

    public void controlIsLoanNotAlreadyPaidOff(Credit loaLoan) {
        if (loaLoan.getCreditStatusType() == CreditStatusType.PAID) {
            throw new IllegalFieldException(CreditErrorMessage.LOAN_ALREADY_PAID_OFF);
        }
    }
}
