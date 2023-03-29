package com.example.pi_ease.Services.Classes;
import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DAO.Entities.CreditStatusType;
import com.example.pi_ease.DAO.Entities.Tranche;
import com.example.pi_ease.DTO.*;
import com.example.pi_ease.Mapper.CreditMapper;
import com.example.pi_ease.Mapper.TrancheMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditService {
    private final CreditValidationService creditValidationService;
    private final CreditEntityService creditEntityService;
    private final TrancheEntityService trancheEntityService;

    private final BigDecimal INTEREST_RATE = BigDecimal.valueOf(1.59/100);
    private final BigDecimal TAX_RATE = BigDecimal.valueOf(20/100); //KKDF + BSMV
    private final BigDecimal ALLOCATION_FEE = BigDecimal.valueOf(45);
    private final int INSTALLMENT_COUNT_LIMIT = 360;

    public CrCalculateCreditResponseDto calculateCredit(int tranche, BigDecimal montant_demander) {

        creditValidationService.controlIsParameterNotNull(tranche,montant_demander);

        BigDecimal nbTranche = BigDecimal.valueOf(tranche);

        BigDecimal taux_intérêt_total = INTEREST_RATE.add(TAX_RATE);

        BigDecimal maturity = (nbTranche
                .multiply(BigDecimal.valueOf(30))).divide(BigDecimal.valueOf(36500),RoundingMode.CEILING);

        BigDecimal interet_tot = (montant_demander.multiply(taux_intérêt_total)).multiply(maturity).multiply(nbTranche);
        BigDecimal total_a_payer = montant_demander.add(interet_tot).add(ALLOCATION_FEE);

        BigDecimal monthlyInstallmentAmount = total_a_payer.divide(nbTranche,RoundingMode.CEILING);

        BigDecimal taux_coût_annuel = taux_intérêt_total.multiply(BigDecimal.valueOf(12));

        creditValidationService.controlIsInterestRateNotNegative(INTEREST_RATE);
        creditValidationService.controlIsTaxRateNotNegative(TAX_RATE);
        creditValidationService.controlIsInstallmentAmountPositive(monthlyInstallmentAmount);
        creditValidationService.controlIsTotalPaymentPositive(total_a_payer);

        CrCalculateCreditResponseDto crCalculateCreditResponseDto = new CrCalculateCreditResponseDto();

        crCalculateCreditResponseDto.setInterestRate(INTEREST_RATE);
        crCalculateCreditResponseDto.setTotalInterest(interet_tot);
        crCalculateCreditResponseDto.setMonthlyInstallmentAmount(monthlyInstallmentAmount);
        crCalculateCreditResponseDto.setTotalPayment(total_a_payer);
        crCalculateCreditResponseDto.setAnnualCostRate(taux_coût_annuel);
        crCalculateCreditResponseDto.setAllocationFee(ALLOCATION_FEE);

        return crCalculateCreditResponseDto;
    }

    public CrCalculateLateFeeResponseDto calculateLateFee(int id) {

        Credit credit = CreditEntityService.getByIdWithControl(id);

        CrCalculateLateFeeResponseDto crCalculateLateFeeResponseDto = calculateLateFeeAndUpdateLoan(credit);

        return crCalculateLateFeeResponseDto;
    }

    private CrCalculateLateFeeResponseDto calculateLateFeeAndUpdateLoan(Credit credit) {

        LocalDate dueDate = credit.getDueDate();

        Long lateDayCount = CreditValidationService.controlIsLoanDueDatePast(dueDate);

        BigDecimal totalCredit = credit.getMontant_demander();

        BigDecimal Taux_frais_retard = INTEREST_RATE.add((INTEREST_RATE.multiply(BigDecimal.valueOf(30/100))));
        BigDecimal total_frais_retard = ((totalCredit.multiply(BigDecimal.valueOf(lateDayCount))).multiply(Taux_frais_retard))
                .divide(BigDecimal.valueOf(30),RoundingMode.UP);

        BigDecimal impôt_sur_interet_retard = total_frais_retard.multiply(TAX_RATE);

        total_frais_retard = total_frais_retard.add(impôt_sur_interet_retard);

        BigDecimal soldeRestant = credit.getRestApay();
        soldeRestant = soldeRestant.add(total_frais_retard);

        CreditValidationService.controlIsInterestRateNotNegative(INTEREST_RATE);
        CreditValidationService.controlIsLateFeeRateNotNegative(Taux_frais_retard);
        CreditValidationService.controlIsTotalLateFeePositive(total_frais_retard);
        CreditValidationService.controlIsLateInterestTaxNotNegative(impôt_sur_interet_retard);
        CreditValidationService.controlIsPrincipalLoanAmountPositive(soldeRestant);

        credit.setCreditStatusType(CreditStatusType.LATE);
        credit.setRestApay(soldeRestant);

        CreditEntityService.save(credit);

        CrCalculateLateFeeResponseDto loaCalculateLateFeeResponseDto = new CrCalculateLateFeeResponseDto();

        loaCalculateLateFeeResponseDto.setLateFeeRate(Taux_frais_retard);
        loaCalculateLateFeeResponseDto.setTotalLateFee(total_frais_retard);
        loaCalculateLateFeeResponseDto.setLateInterestTax(impôt_sur_interet_retard);
        loaCalculateLateFeeResponseDto.setLateDayCount(lateDayCount);

        return loaCalculateLateFeeResponseDto;
    }

    public CreditDto findLoanById(int id) {

        Credit credit = CreditEntityService.getByIdWithControl(id);

        updateLoanIfDueDatePast(credit);
        credit = CreditEntityService.getByIdWithControl(id);

        CreditDto creditDto = CreditMapper.INSTANCE.convertToCreditDto(credit);

        return creditDto;
    }

    public CreditDto applyLoan(CrApplyCreditDto applyCreditDto) {

        CreditValidationService.controlIsParameterNotNull(applyCreditDto);

        //int customerId = CreditEntityService.getCurrentCustomerId();
        BigDecimal principalLoanAmount = applyCreditDto.getMontant();
        Integer nbTranche = applyCreditDto.getNbT();
        BigDecimal nbT = BigDecimal.valueOf(nbTranche);
        BigDecimal salaireMonsuel = applyCreditDto.getMonthlySalary();

        Credit credit = CreditMapper.INSTANCE.convertToCredit(applyCreditDto);
        Tranche tranche= TrancheMapper.INSTANCE.convertToTranche(applyCreditDto);
        BigDecimal total_taux_interet = INTEREST_RATE.add(TAX_RATE);

        BigDecimal maturity = (nbT
                .multiply(BigDecimal.valueOf(30))).divide(BigDecimal.valueOf(36500),RoundingMode.CEILING);
        BigDecimal totalInterest = (principalLoanAmount.multiply(total_taux_interet)).multiply(maturity).multiply(nbT);

        BigDecimal totalPayment = principalLoanAmount.add(totalInterest).add(ALLOCATION_FEE);

        BigDecimal monthlyInstallmentAmount = totalPayment.divide(nbT,RoundingMode.CEILING);

        BigDecimal maxInstallmentAmount = salaireMonsuel.multiply(BigDecimal.valueOf(0.5));
        BigDecimal maxLoanAmount = (maxInstallmentAmount
                .multiply(nbT))
                .multiply(BigDecimal.valueOf(0.80));

        LocalDate dueDate = LocalDate.now().plusMonths(nbTranche);

        CreditValidationService.controlIsInterestRateNotNegative(INTEREST_RATE);
        CreditValidationService.controlIsMonthlyInstallmentAmountPositive(monthlyInstallmentAmount);
        CreditValidationService.controlIsInterestAmountNotNegative(totalInterest);
        CreditValidationService.controlIsPrincipalLoanAmountPositive(principalLoanAmount);
        CreditValidationService.controlIsLoanAmountNotGreaterThanMaxLoanAmount(
                principalLoanAmount, maxLoanAmount);
        CreditValidationService.controlIsInstallmentCountNotGreaterThanInstallmentCountLimit(nbTranche,INSTALLMENT_COUNT_LIMIT);

       // Credit.setCustomerId(customerId);
        tranche.setMonPay(monthlyInstallmentAmount);
        credit.setTauxInteret(totalInterest);
        credit.setMonPP(principalLoanAmount);
        credit.setRestApay(principalLoanAmount);
        credit.setDueDate(dueDate);
        credit.setCreditStatusType(CreditStatusType.CONTINUING);

        credit = CreditEntityService.save(credit);

        CreditDto creditDto = CreditMapper.INSTANCE.convertToCreditDto(credit);

        return creditDto;
    }


    public CrPayInstallmentResponseDto payInstallment(int id) {

        Credit credit = CreditEntityService.getByIdWithControl(id);
        Tranche tranche=TrancheEntityService.getByIdWithControl(id);
        updateLoanIfDueDatePast(credit);

        BigDecimal tranchePay = tranche.getMonPay();
        BigDecimal solderestant = credit.getRestApay();

        solderestant = solderestant.subtract(tranchePay);

       CreditValidationService.controlIsRemainingPrincipalNotNegative(solderestant);
        CreditValidationService.controlIsInstallmentAmountPositive(tranchePay);

        credit.setRestApay(solderestant);

        Tranche tranche1 = new Tranche();

        tranche1.setId_T(id);
        tranche1.setMonPay(tranchePay);
        tranche1.getPayDate(LocalDate.now());

        credit = CreditEntityService.save(credit);
        tranche1 = TrancheEntityService.save(tranche1);

        CrPayInstallmentResponseDto crPayInstallmentResponseDto = convertToLoaPayInstallmentResponseDto(credit, tranche);

        return crPayInstallmentResponseDto;
    }

    private void updateLoanIfDueDatePast(Credit loaLoan) {

        LocalDate dueDate = loaLoan.getDueDate();

        long lateDayCount = ChronoUnit.DAYS.between(dueDate, LocalDate.now());

        if(lateDayCount > 0 ){
            calculateLateFeeAndUpdateLoan(loaLoan);
        }
    }

    private CrPayInstallmentResponseDto  convertToLoaPayInstallmentResponseDto(Credit credit, Tranche tranche){

        int idTranche = tranche.getId_T();
        BigDecimal paymentAmount = tranche.getMonPay();
        LocalDate PaymentDate = tranche.getPayDate();

        BigDecimal remainingPrincipal = credit.getRestApay();
        LocalDate dueDate = credit.getDueDate();

        CrPayInstallmentResponseDto crPayInstallmentResponseDto = new CrPayInstallmentResponseDto();

        crPayInstallmentResponseDto.setIdCrPay(idTranche);
        crPayInstallmentResponseDto.setPaymentAmount(paymentAmount);
        crPayInstallmentResponseDto.setPaymentDate(PaymentDate);
        crPayInstallmentResponseDto.setRemainingPrincipal(remainingPrincipal);
        crPayInstallmentResponseDto.setDueDate(dueDate);

        return crPayInstallmentResponseDto;
    }

    public CrPayCreditOffResponseDto payLoanOff(int id) {

        Credit credit = CreditEntityService.getByIdWithControl(id);

        updateLoanIfDueDatePast(credit);

        BigDecimal paidAmount = credit.getRestApay();
        BigDecimal remainingPrincipal = BigDecimal.ZERO;

        CreditValidationService.controlIsLoanNotAlreadyPaidOff(credit);
        CreditValidationService.controlIsRemainingPrincipalNotNegative(remainingPrincipal);

        credit.setRestApay(remainingPrincipal);
        credit.setCreditStatusType(CreditStatusType.PAID);

        credit = CreditEntityService.save(credit);

        CrPayCreditOffResponseDto crPayCreditOffResponseDto = CrPayCreditOffResponseDto.INSTANCE.convertToLoaPayLoanOffResponseDto(credit);

        crPayCreditOffResponseDto.setRestApay(remainingPrincipal);
        crPayCreditOffResponseDto.setMonPay(paidAmount);

        return crPayCreditOffResponseDto;
    }
}
