package com.example.pi_ease.Services.Classes;
import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DAO.Entities.CreditStatusType;
import com.example.pi_ease.DTO.CrApplyCreditDto;
import com.example.pi_ease.Exceptions.CreditErrorMessage;
import com.example.pi_ease.Exceptions.IllegalFieldException;
import com.example.pi_ease.Exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditValidationService {

 //   private final CusCustomerEntityService cusCustomerEntityService;
//UserRihem !!!!!!!!!!!!!!!!!!!!!!!!
    public void controlIsParameterNotNull(int T, BigDecimal montant_demander) {

        boolean hasNull = T == 0 || montant_demander == null;

        if(hasNull){
            throw new IllegalFieldException(CreditErrorMessage.PARAMETER_CANNOT_BE_NULL);
        }
    }

    public static void controlIsParameterNotNull(CrApplyCreditDto crApplyCreditDto) {

        boolean hasNull = crApplyCreditDto.getNbT()== 0     ||
                crApplyCreditDto.getMontant() == null  ||
                crApplyCreditDto.getMonthlySalary() == null;

        if(hasNull){
            throw new IllegalFieldException(CreditErrorMessage.PARAMETER_CANNOT_BE_NULL);
        }
    }

    public static void controlIsInterestRateNotNegative(BigDecimal interestRate) {

        if(interestRate.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalFieldException(CreditErrorMessage.INTEREST_RATE_CANNOT_BE_NEGATIVE);
        }
    }


    public static void controlIsInstallmentAmountPositive(BigDecimal monthlyInstallmentAmount) {

        if(monthlyInstallmentAmount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalFieldException(CreditErrorMessage.INSTALLMENT_AMOUNT_MUST_BE_POSITIVE);
        }
    }

    public void controlIsTotalPaymentPositive(BigDecimal totalPayment) {

        if(totalPayment.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalFieldException(CreditErrorMessage.TOTAL_PAYMENT_MUST_BE_POSITIVE);
        }
    }

    public static void controlIsLateFeeRateNotNegative(BigDecimal lateFeeRate) {

        if(lateFeeRate.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalFieldException(CreditErrorMessage.LATE_FEE_RATE_CANNOT_BE_NEGATIVE);
        }
    }

    public static void controlIsTotalLateFeePositive(BigDecimal totalLateFee) {

        if(totalLateFee.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalFieldException(CreditErrorMessage.TOTAL_LATE_FEE_MUST_BE_POSITIVE);
        }
    }

    public static void controlIsLateInterestTaxNotNegative(BigDecimal lateInterestTax) {

        if(lateInterestTax.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalFieldException(CreditErrorMessage.LATE_INTEREST_TAX_CANNOT_BE_NEGATIVE);
        }
    }

    public static void controlIsLoanAmountNotGreaterThanMaxLoanAmount(BigDecimal principalLoanAmount, BigDecimal maxLoanAmount) {

        if(principalLoanAmount.compareTo(maxLoanAmount)>0){

            CreditErrorMessage CrErrorMessage = CreditErrorMessage.LOAN_AMOUNT_CANNOT_BE_GREATER_THAN_MAX_AMOUNT;
            CrErrorMessage.setDetailMessage(String.valueOf(maxLoanAmount));

            throw new IllegalFieldException(CrErrorMessage);
        }
    }

  /*  public void controlIsCustomerExist(int customerId) {

        boolean isExist = cusCustomerEntityService.existsById(customerId);

        if (!isExist){

            throw new ItemNotFoundException(CreditErrorMessage.CUSTOMER_NOT_FOUND);
        }
    }*/

    public static void controlIsMonthlyInstallmentAmountPositive(BigDecimal monthlyInstallmentAmount) {

        if(monthlyInstallmentAmount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalFieldException(CreditErrorMessage.MONTHLY_INSTALLMENT_AMOUNT_MUST_BE_POSITIVE);
        }
    }

    public static void controlIsInterestAmountNotNegative(BigDecimal interestAmount) {

        if(interestAmount.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalFieldException(CreditErrorMessage.INTEREST_AMOUNT_CANNOT_BE_NEGATIVE);
        }
    }

    public static void controlIsPrincipalLoanAmountPositive(BigDecimal principalLoanAmount) {

        if(principalLoanAmount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalFieldException(CreditErrorMessage.PRINCIPAL_lOAN_AMOUNT_MUST_BE_POSITIVE);
        }
    }

    public static Long controlIsLoanDueDatePast(LocalDate dueDate) {

        LocalDate now = LocalDate.now();
        long lateDayCount = ChronoUnit.DAYS.between(dueDate, now);

        if(lateDayCount < 1 ){

            CreditErrorMessage CrErrorMessage = CreditErrorMessage.DUE_DATE_HAS_NOT_PASSED_YET;
            CrErrorMessage.setDetailMessage(String.valueOf(dueDate));

            throw new IllegalFieldException(CrErrorMessage);
        }
        return lateDayCount;
    }

    public static void controlIsRemainingPrincipalNotNegative(BigDecimal resteApayer) {
        if(resteApayer.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalFieldException(CreditErrorMessage.REMAINING_PRINCIPAL_MUST_BE_POSITIVE);
        }
    }

    public void controlIsTaxRateNotNegative(BigDecimal taxRate) {
        if(taxRate.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalFieldException(CreditErrorMessage.TAX_RATE_CANNOT_BE_NEGATIVE);
        }
    }

    public static void controlIsInstallmentCountNotGreaterThanInstallmentCountLimit(int nbT, int nbLimT) {
        if(nbT>nbLimT){

            CreditErrorMessage CrErrorMessage = CreditErrorMessage.INSTALLMENT_COUNT_CANNOT_BE_LARGER_THAN_LIMIT;
            CrErrorMessage.setDetailMessage(String.valueOf(nbLimT));

            throw new IllegalFieldException(CrErrorMessage);
        }
    }

    public static void controlIsLoanNotAlreadyPaidOff(Credit credit) {
        if(credit.getCreditStatusType() == CreditStatusType.PAID){
            throw new IllegalFieldException(CreditErrorMessage.LOAN_ALREADY_PAID_OFF);
        }
    }
}
