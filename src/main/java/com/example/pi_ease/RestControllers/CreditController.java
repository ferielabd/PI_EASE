package com.example.pi_ease.RestControllers;



import com.example.pi_ease.DAO.Entities.Credit;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public BigDecimal /*ResponseEntity<RestResponse<CrCalculateCreditResponseDto>>*/ calculateLoan(@RequestParam Integer installmentCount, @RequestParam BigDecimal principalLoanAmount) {

       // CrCalculateCreditResponseDto
                BigDecimal loaCalculateLoanResponseDto = service.calculateLoan(installmentCount, principalLoanAmount);

        return loaCalculateLoanResponseDto;
    }

    @Operation(
            tags = "Loan Controller",
            summary = "Calculate late fee.",
            description = "Calculate the late fee."
    )
    @GetMapping("/calculate-late-fee/{id}")
    public BigDecimal /*ResponseEntity<RestResponse<CrCalculateLateFeeResponseDto>>*/ calculateLateFee(@PathVariable Long id) {

        BigDecimal loaCalculateLateFeeResponseDto = service.calculateLateFee(id);

        //return ResponseEntity.ok(RestResponse.of(loaCalculateLateFeeResponseDto));
        return loaCalculateLateFeeResponseDto;
    }

    @Operation(
            tags = "Loan Controller",
            summary = "Get a Loan",
            description = "Gets a loan by id."
    )
    @GetMapping("/{id}")
    public Credit findLoanById(@PathVariable Long id) {

        Credit loaLoanDto = service.findLoanById(id);

        return loaLoanDto;
    }

    @Operation(
            tags = "Loan Controller",
            summary = "Apply loan.",
            description = "Apply for a loan."
    )
    @PostMapping("/apply-loan")
    public ResponseEntity<?> applyLoan(@RequestBody CrApplyCreditDto loaApplyLoanDto) {

        CreditDto loaLoanDto = service.applyLoan(loaApplyLoanDto);

        // return ResponseEntity.ok(RestResponse.of(loaLoanDto));
        Map<String, String> MSG = new HashMap<>();
        MSG.put("message","Demande de crédit crée !");
        return ResponseEntity.ok(MSG);
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
    public ResponseEntity<?> payLoanOff(@PathVariable Long id) {

        CrPayCreditOffResponseDto loaPayLoanOffResponseDto = service.payLoanOff(id);

        Map<String, String> MSG = new HashMap<>();
        MSG.put("message","Crédit payé !");
        // return ResponseEntity.ok(RestResponse.of(loaPayLoanOffResponseDto));
        return ResponseEntity.ok(MSG);
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