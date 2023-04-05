package com.example.pi_ease.RestController;


import com.example.pi_ease.Message.InvestmentDTO.Request;
import com.example.pi_ease.Message.InvestmentDTO.Response;
import com.example.pi_ease.Service.Interfaces.IInvestmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Investment")
@AllArgsConstructor
public class InvestmentRestController {
    @Autowired
    private IInvestmentService investmentService;

    @PostMapping("/users/{userId}/investments/{projectId}/{portfolioId}")
    public ResponseEntity<Response> createInvestment(@PathVariable int userId,
                                                     @PathVariable int projectId,
                                                     @PathVariable int portfolioId,

                                                     @RequestBody Request investmentDTO) {
        Response response = investmentService.createInvestment(projectId, investmentDTO,userId,portfolioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Add endpoint for getting user investments
    @GetMapping("/users/{userId}/investments")
    public ResponseEntity<List<Response>> getInvestments(@PathVariable int userId) {
        List<Response> investments = investmentService.getInvestments(userId);
        return ResponseEntity.ok(investments);

    }



}
