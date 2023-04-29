package com.example.pi_ease.RestControllers;



import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DAO.Entities.CreditHistory;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DTO.*;
import com.example.pi_ease.Services.Classes.CreditDecisionService;
import com.example.pi_ease.Services.Classes.CreditService;
import com.example.pi_ease.Services.Classes.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private  AuthController authController;
    @Autowired
    private EmailService emailService;

    @Operation(
            tags = "Loan Controller",
            summary = "Calculate loan.",
            description = "Calculate the loan."
    )
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/calculate-loan")
    public List<BigDecimal > calculateLoan(@RequestParam Integer installmentCount, @RequestParam BigDecimal principalLoanAmount) {


              List<BigDecimal>   loaCalculateLoanResponseDto = service.calculateLoan(installmentCount, principalLoanAmount);

        return loaCalculateLoanResponseDto;
    }


    @Operation(
            tags = "Loan Controller",
            summary = "Get a Loan",
            description = "Gets a loan by id."
    )
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/{id}")
    public Credit findLoanById(@PathVariable Long id) {

        Credit loaLoanDto = service.findLoanById(id);

        return loaLoanDto;
    }
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/loanHis/{id}")
    public CreditHistory findLoanHisById(@PathVariable Long id) {

        CreditHistory loaLoanDto = service.getCH(id);

        return loaLoanDto;
    }
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/AllLoan")
    public List<Credit> allLoan() {

        List<Credit> allC = service.allLoan();

        return allC;
    }


    @Operation(
            tags = "Loan Controller",
            summary = "Apply loan.",
            description = "Apply for a loan."
    )
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/apply-loan")
    public ResponseEntity<?> applyLoan(@RequestBody CrApplyCreditDto loaApplyLoanDto) {

        CreditDto loaLoanDto = service.applyLoan(loaApplyLoanDto);
        /*  return ResponseEntity.ok(RestResponse.of(loaLoanDto));*/
        Map<String, String> MSG = new HashMap<>();
        MSG.put("message","Demande de crédit crée !?En attente d'pprouver la demande");
        return ResponseEntity.ok(MSG);
    }


    @Operation(
            tags = "Loan Controller",
            summary = "Pay installment.",
            description = "Pay installment of the loan."
    )
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/pay-installment/{id}")
    public ResponseEntity<?> payInstallment(@PathVariable Long id) {

        CrPayInstallmentResponseDto loaPayInstallmentResponseDto = service.payInstallment(id);

        Map<String, String> MSG = new HashMap<>();
        MSG.put("message","le paiement mensuel est effectué sans problème");
        return ResponseEntity.ok(MSG);
    }


    @Operation(
            tags = "Loan Controller",
            summary = "Pay loan off",
            description = "Pay the remaining amount and close the loan. "
    )
    @CrossOrigin(origins="http://localhost:4200")
    @DeleteMapping("/pay-loan-off/{id}")
    public ResponseEntity<?> payLoanOff(@PathVariable Long id) {

        CrPayCreditOffResponseDto loaPayLoanOffResponseDto = service.payLoanOff(id);

        Map<String, String> MSG = new HashMap<>();
        MSG.put("message","Crédit payé !");
        return ResponseEntity.ok(MSG);
    }

    @Operation(
            tags = "Loan Controller",
            summary = "Calculate late fee.",
            description = "Calculate the late fee."
    )
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/calculate-late-fee/{id}")
    public BigDecimal  calculateLateFee(@PathVariable Long id) {

        BigDecimal loaCalculateLateFeeResponseDto = service.calculateLateFee(id);

        return loaCalculateLateFeeResponseDto;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/credit-request/{id}")
    public ResponseEntity<?>  processCreditRequest(@RequestBody CreditRequest creditRequest,@PathVariable long id) {
        boolean isApproved = creditDecisionService.isCreditApproved(creditRequest);
        Map<String, String> MSG = new HashMap<>();
        if (isApproved) {
            service.UpDateStatusLoan(id);
        } else {
            User user = authController.getFakeUser();
            this.emailService.sendSimpleEmail(user.getEmail(),"Décision", "your loan is not Approved, and you are not solvency");

        }
        MSG.put("message","Check your email!!");
        return ResponseEntity.ok(MSG);

    }

}