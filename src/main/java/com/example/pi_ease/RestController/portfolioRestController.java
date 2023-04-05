package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.Portfolio;
import com.example.pi_ease.Service.Interfaces.IPortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("Portfolio")
public class portfolioRestController {
    private IPortfolioService iPortfolioService;
    @GetMapping("/afficherPortfolio")
    public List<Portfolio> afficher(){
        return iPortfolioService.selectAll();
    }
    @PostMapping("/ajouterPortfolio")
    public Portfolio ajouter ( @RequestBody Portfolio portfolio){
        return iPortfolioService.add(portfolio);    }
    @GetMapping("/afficherPortfolioAvecId/{id}")
    public Portfolio afficherAvecId(@PathVariable int idInv){
        return iPortfolioService.selectByID(idInv);
    }
    @PostMapping("/ajouterAllPortfolio")
    public List<Portfolio> addAll(@RequestBody List<Portfolio> list){
        return iPortfolioService.addAll(list);
    }
    @DeleteMapping("/supprimerPortfolio")
    public void  delete(@RequestBody Portfolio portfolio){
        iPortfolioService.delete(portfolio);
    }
    @PutMapping("/modifierPortfolio")
    public Portfolio edit(@RequestBody Portfolio portfolio){
        return iPortfolioService.edit(portfolio);
    }
    @DeleteMapping("/supprimerPortfolioById")
    public void deleteById(@RequestParam int idInv){
        iPortfolioService.deleteById(idInv);
    }
    @GetMapping("/afficherTotalInvesting/{idP}")
    public double getTotalInvestmentValue(@PathVariable int idP){
        return iPortfolioService.getTotalInvestmentValue(idP);
    }
    @GetMapping("/{id}/total-people-lifted-out-of-poverty")
    public int getTotalPeopleLiftedOutOfPoverty(@PathVariable("id") int portfolioId) {
        return iPortfolioService.getTotalPeopleLiftedOutOfPoverty(portfolioId);
    }

    @GetMapping("/{id}/total-jobs-created")
    public int getTotalJobsCreated(@PathVariable("id") int portfolioId) {
        return iPortfolioService.getTotalJobsCreated(portfolioId);
    }

    @GetMapping("/{id}/total-women-entrepreneurs-supported")
    public int getTotalWomenEntrepreneursSupported(@PathVariable("id") int portfolioId) {
        return iPortfolioService.getTotalWomenEntrepreneursSupported(portfolioId);
    }
}
