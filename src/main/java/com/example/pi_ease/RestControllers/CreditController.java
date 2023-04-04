package com.example.pi_ease.RestControllers;



import com.example.pi_ease.DTO.*;
import com.example.pi_ease.Services.Classes.CreditDecisionService;
import com.example.pi_ease.Services.Classes.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/Credit")
public class CreditController {
    @Autowired
    CreditService service;
    @Autowired
    private CreditDecisionService creditDecisionService;

    @Operation(
            tags = "Loan Controller",
            summary = "Calculate loan.",
            description = "Calculate the loan."
    )
    @GetMapping("/calculate-loan")
    public ResponseEntity<RestResponse<CrCalculateCreditResponseDto>> calculateLoan(@RequestParam Integer installmentCount, @RequestParam BigDecimal principalLoanAmount) {

        CrCalculateCreditResponseDto loaCalculateLoanResponseDto = service.calculateLoan(installmentCount, principalLoanAmount);

        return ResponseEntity.ok(RestResponse.of(loaCalculateLoanResponseDto));
    }

    @Operation(
            tags = "Loan Controller",
            summary = "Calculate late fee.",
            description = "Calculate the late fee."
    )
    @GetMapping("/calculate-late-fee/{id}")
    public ResponseEntity<RestResponse<CrCalculateLateFeeResponseDto>> calculateLateFee(@PathVariable Long id) {

        CrCalculateLateFeeResponseDto loaCalculateLateFeeResponseDto = service.calculateLateFee(id);

        return ResponseEntity.ok(RestResponse.of(loaCalculateLateFeeResponseDto));
    }

    @Operation(
            tags = "Loan Controller",
            summary = "Get a Loan",
            description = "Gets a loan by id."
    )
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<CreditDto>> findLoanById(@PathVariable Long id) {

        CreditDto loaLoanDto = service.findLoanById(id);

        return ResponseEntity.ok(RestResponse.of(loaLoanDto));
    }

    @Operation(
            tags = "Loan Controller",
            summary = "Apply loan.",
            description = "Apply for a loan."
    )
    @PostMapping("/apply-loan")
    public ResponseEntity<RestResponse<CreditDto>> applyLoan(@RequestBody CrApplyCreditDto loaApplyLoanDto) {

        CreditDto loaLoanDto = service.applyLoan(loaApplyLoanDto);

        return ResponseEntity.ok(RestResponse.of(loaLoanDto));
    }

    @Operation(
            tags = "Loan Controller",
            summary = "Pay installment.",
            description = "Pay installment of the loan."
    )
    @PostMapping("/pay-installment/{id}")
    public ResponseEntity<RestResponse<CrPayInstallmentResponseDto>> payInstallment(@PathVariable Long id) {

        CrPayInstallmentResponseDto loaPayInstallmentResponseDto = service.payInstallment(id);

        return ResponseEntity.ok(RestResponse.of(loaPayInstallmentResponseDto));
    }

    @Operation(
            tags = "Loan Controller",
            summary = "Pay loan off",
            description = "Pay the remaining amount and close the loan. "
    )
    @DeleteMapping("/pay-loan-off/{id}")
    public ResponseEntity<RestResponse<CrPayCreditOffResponseDto>> payLoanOff(@PathVariable Long id) {

        CrPayCreditOffResponseDto loaPayLoanOffResponseDto = service.payLoanOff(id);

        return ResponseEntity.ok(RestResponse.of(loaPayLoanOffResponseDto));
    }


    @PostMapping("/credit-request")
    public String processCreditRequest(@ModelAttribute("creditRequest") CreditRequest creditRequest, Model model) {
        boolean isApproved = creditDecisionService.isCreditApproved(creditRequest);
        if (isApproved) {
            model.addAttribute("result", "Credit approved!");
        } else {
            model.addAttribute("result", "Credit not approved.");
        }
        return "credit-result";


    }

}