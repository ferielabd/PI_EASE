package com.example.pi_ease.Services.Classes;
import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DAO.Entities.CreditStatusType;
import com.example.pi_ease.DAO.Entities.Tranche;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DTO.*;
import com.example.pi_ease.Mapper.CreditMapper;
import com.example.pi_ease.RestControllers.AuthController;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
public class CreditService {

    private final CreditValidationService creditValidationService;
    private final CreditEntityService creditEntityService;
    private final TrancheEntityService trancheEntityService;
    private final AuthController authController;

    public CreditService(CreditValidationService creditValidationService, CreditEntityService creditEntityService, TrancheEntityService trancheEntityService, AuthController authController) {
        this.creditValidationService = creditValidationService;
        this.creditEntityService = creditEntityService;
        this.trancheEntityService = trancheEntityService;
        this.authController = authController;
    }

    private final BigDecimal INTEREST_RATE = BigDecimal.valueOf(8.02/100);
    private final BigDecimal TAX_RATE = BigDecimal.valueOf(20/100);
    private final BigDecimal ALLOCATION_FEE = BigDecimal.valueOf(45);
    private final int INSTALLMENT_COUNT_LIMIT = 360;

    public  CrCalculateCreditResponseDto calculateLoan(Integer installment, BigDecimal principalLoanAmount) {

        creditValidationService.controlIsParameterNotNull(installment,principalLoanAmount);

        BigDecimal installmentCount = BigDecimal.valueOf(installment);

        BigDecimal totalInterestRate = INTEREST_RATE.add(TAX_RATE);

        BigDecimal maturity = (installmentCount
                .multiply(BigDecimal.valueOf(30))).divide(BigDecimal.valueOf(36500),RoundingMode.CEILING);

        BigDecimal totalInterest = (principalLoanAmount.multiply(totalInterestRate)).multiply(maturity).multiply(installmentCount);
        BigDecimal totalPayment = principalLoanAmount.add(totalInterest).add(ALLOCATION_FEE);

        BigDecimal monthlyInstallmentAmount = totalPayment.divide(installmentCount,RoundingMode.CEILING);

        BigDecimal annualCostRate = totalInterestRate.multiply(BigDecimal.valueOf(12));

        creditValidationService.controlIsInterestRateNotNegative(INTEREST_RATE);
        creditValidationService.controlIsTaxRateNotNegative(TAX_RATE);
        creditValidationService.controlIsInstallmentAmountPositive(monthlyInstallmentAmount);
        creditValidationService.controlIsTotalPaymentPositive(totalPayment);

        CrCalculateCreditResponseDto loaCalculateLoanResponseDto = new CrCalculateCreditResponseDto();

        loaCalculateLoanResponseDto.setInterestRate(INTEREST_RATE);
        loaCalculateLoanResponseDto.setTotalInterest(totalInterest);
        loaCalculateLoanResponseDto.setMonthlyInstallmentAmount(monthlyInstallmentAmount);
        loaCalculateLoanResponseDto.setTotalPayment(totalPayment);
        loaCalculateLoanResponseDto.setAnnualCostRate(annualCostRate);
        loaCalculateLoanResponseDto.setAllocationFee(ALLOCATION_FEE);

        return loaCalculateLoanResponseDto;
    }

    public CrCalculateLateFeeResponseDto calculateLateFee(Long id) {

        Credit loaLoan = creditEntityService.getByIdWithControl(id);

        CrCalculateLateFeeResponseDto loaCalculateLateFeeResponseDto = calculateLateFeeAndUpdateLoan(loaLoan);

        return loaCalculateLateFeeResponseDto;
    }

    private CrCalculateLateFeeResponseDto calculateLateFeeAndUpdateLoan(Credit loaLoan) {

        LocalDate dueDate = loaLoan.getDueDate();

        Long lateDayCount = creditValidationService.controlIsLoanDueDatePast(dueDate);

        BigDecimal totalLoan = loaLoan.getPrincipalLoanAmount();

        BigDecimal lateFeeRate = INTEREST_RATE.add((INTEREST_RATE.multiply(BigDecimal.valueOf(30/100))));
        BigDecimal totalLateFee = ((totalLoan.multiply(BigDecimal.valueOf(lateDayCount))).multiply(lateFeeRate))
                .divide(BigDecimal.valueOf(30),RoundingMode.UP);

        BigDecimal lateInterestTax = totalLateFee.multiply(TAX_RATE);

        totalLateFee = totalLateFee.add(lateInterestTax);

        BigDecimal remainingPrincipal = loaLoan.getRemainingPrincipal();
        remainingPrincipal = remainingPrincipal.add(totalLateFee);

        creditValidationService.controlIsInterestRateNotNegative(INTEREST_RATE);
        creditValidationService.controlIsLateFeeRateNotNegative(lateFeeRate);
        creditValidationService.controlIsTotalLateFeePositive(totalLateFee);
        creditValidationService.controlIsLateInterestTaxNotNegative(lateInterestTax);
        creditValidationService.controlIsPrincipalLoanAmountPositive(remainingPrincipal);

        loaLoan.setCreditStatusType(CreditStatusType.LATE);
        loaLoan.setRemainingPrincipal(remainingPrincipal);

        creditEntityService.save(loaLoan);

        CrCalculateLateFeeResponseDto loaCalculateLateFeeResponseDto = new CrCalculateLateFeeResponseDto();

        loaCalculateLateFeeResponseDto.setLateFeeRate(lateFeeRate);
        loaCalculateLateFeeResponseDto.setTotalLateFee(totalLateFee);
        loaCalculateLateFeeResponseDto.setLateInterestTax(lateInterestTax);
        loaCalculateLateFeeResponseDto.setLateDayCount(lateDayCount);

        return loaCalculateLateFeeResponseDto;
    }

    public CreditDto findLoanById(Long id) {

        Credit credit = creditEntityService.getByIdWithControl(id);

        updateLoanIfDueDatePast(credit);
        credit =creditEntityService.getByIdWithControl(id);

        CreditDto loaLoanDto = CreditMapper.INSTANCE.convertToCreditDto(credit);

        return loaLoanDto;
    }

    public CreditDto applyLoan(CrApplyCreditDto loaLoanApplyLoanDto) {

        creditValidationService.controlIsParameterNotNull(loaLoanApplyLoanDto);
        BigDecimal principalLoanAmount = loaLoanApplyLoanDto.getPrincipalLoanAmount();
        Integer installment = loaLoanApplyLoanDto.getInstallmentCount();
        BigDecimal installmentCount = BigDecimal.valueOf(installment);
        BigDecimal monthlySalary = loaLoanApplyLoanDto.getMonthlySalary();

        Credit credit = CreditMapper.INSTANCE.convertToCredit(loaLoanApplyLoanDto);

        BigDecimal totalInterestRate = INTEREST_RATE.add(TAX_RATE);

        BigDecimal maturity = (installmentCount
                .multiply(BigDecimal.valueOf(30))).divide(BigDecimal.valueOf(36500),RoundingMode.CEILING);
        BigDecimal totalInterest = (principalLoanAmount.multiply(totalInterestRate)).multiply(maturity).multiply(installmentCount);

        BigDecimal totalPayment = principalLoanAmount.add(totalInterest).add(ALLOCATION_FEE);

        BigDecimal monthlyInstallmentAmount = totalPayment.divide(installmentCount,RoundingMode.CEILING);

        BigDecimal maxInstallmentAmount = monthlySalary.multiply(BigDecimal.valueOf(0.4));
        BigDecimal maxLoanAmount = (maxInstallmentAmount
                .multiply(installmentCount))
                .multiply(BigDecimal.valueOf(0.80));

        LocalDate dueDate = LocalDate.now().plusMonths(installment);

        creditValidationService.controlIsInterestRateNotNegative(INTEREST_RATE);
        creditValidationService.controlIsMonthlyInstallmentAmountPositive(monthlyInstallmentAmount);
        creditValidationService.controlIsInterestAmountNotNegative(totalInterest);
        creditValidationService.controlIsPrincipalLoanAmountPositive(principalLoanAmount);
        creditValidationService.controlIsLoanAmountNotGreaterThanMaxLoanAmount(
                principalLoanAmount, maxLoanAmount);
        creditValidationService.controlIsInstallmentCountNotGreaterThanInstallmentCountLimit(installment,INSTALLMENT_COUNT_LIMIT);


        User user = authController.getCurrentUser();

        credit. setUserCredit(user);
        credit.setMonthlyInstallmentAmount(monthlyInstallmentAmount);
        credit.setInterestToBePaid(totalInterest);
        credit.setPrincipalToBePaid(principalLoanAmount);
        credit.setRemainingPrincipal(principalLoanAmount);
        credit.setDueDate(dueDate);
        credit.setCreditStatusType(CreditStatusType.CONTINUING);

        credit = creditEntityService.save(credit);

        CreditDto creditDto = CreditMapper.INSTANCE.convertToCreditDto(credit);

        return creditDto;
    }


    public CrPayInstallmentResponseDto payInstallment(Long id) {

        Credit credit = creditEntityService.getByIdWithControl(id);

        updateLoanIfDueDatePast(credit);

        BigDecimal installmentAmount = credit.getMonthlyInstallmentAmount();
        BigDecimal remainingPrincipal = credit.getRemainingPrincipal();

        remainingPrincipal = remainingPrincipal.subtract(installmentAmount);

        creditValidationService.controlIsRemainingPrincipalNotNegative(remainingPrincipal);
        creditValidationService.controlIsInstallmentAmountPositive(installmentAmount);

        credit.setRemainingPrincipal(remainingPrincipal);

        Tranche tranche = new Tranche();

       tranche.setCreditT(credit);
        tranche.setPaymentAmount(installmentAmount);
        tranche.setPaymentDate(LocalDate.now());

        credit = creditEntityService.save(credit);
        tranche = trancheEntityService.save(tranche);

        CrPayInstallmentResponseDto loaPayInstallmentResponseDto = convertToLoaPayInstallmentResponseDto(credit, tranche);

        return loaPayInstallmentResponseDto;
    }

    private void updateLoanIfDueDatePast(Credit credit) {

        LocalDate dueDate = credit.getDueDate();

        long lateDayCount = ChronoUnit.DAYS.between(dueDate, LocalDate.now());

        if(lateDayCount > 0 ){
            calculateLateFeeAndUpdateLoan(credit);
        }
    }

    private CrPayInstallmentResponseDto  convertToLoaPayInstallmentResponseDto(Credit loaLoan, Tranche loanPayment){

        int loanId = loanPayment.getCreditT().getId();
        BigDecimal paymentAmount = loanPayment.getPaymentAmount();
        LocalDate PaymentDate = loanPayment.getPaymentDate();

        BigDecimal remainingPrincipal = loaLoan.getRemainingPrincipal();
        LocalDate dueDate = loaLoan.getDueDate();

        CrPayInstallmentResponseDto loaPayInstallmentResponseDto = new CrPayInstallmentResponseDto();

        loaPayInstallmentResponseDto.setIdCr(loanId);
        loaPayInstallmentResponseDto.setPaymentAmount(paymentAmount);
        loaPayInstallmentResponseDto.setPaymentDate(PaymentDate);
        loaPayInstallmentResponseDto.setRemainingPrincipal(remainingPrincipal);
        loaPayInstallmentResponseDto.setDueDate(dueDate);

        return loaPayInstallmentResponseDto;
    }

    public CrPayCreditOffResponseDto payLoanOff(Long id) {

        Credit loaLoan = creditEntityService.getByIdWithControl(id);

        updateLoanIfDueDatePast(loaLoan);

        BigDecimal paidAmount = loaLoan.getRemainingPrincipal();
        BigDecimal remainingPrincipal = BigDecimal.ZERO;

        creditValidationService.controlIsLoanNotAlreadyPaidOff(loaLoan);
        creditValidationService.controlIsRemainingPrincipalNotNegative(remainingPrincipal);

        loaLoan.setRemainingPrincipal(remainingPrincipal);
        loaLoan.setCreditStatusType(CreditStatusType.PAID);

        loaLoan = creditEntityService.save(loaLoan);

        CrPayCreditOffResponseDto loaPayLoanOffResponseDto = CreditMapper.INSTANCE.convertToCrPayCreditOffResponseDto(loaLoan);

        loaPayLoanOffResponseDto.setRemainingAmount(remainingPrincipal);
        loaPayLoanOffResponseDto.setPaidAmount(paidAmount);

        return loaPayLoanOffResponseDto;
    }




}
