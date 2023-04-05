package com.example.pi_ease.Service.Class;

import com.example.pi_ease.DAO.Entities.Investment;
import com.example.pi_ease.DAO.Entities.Portfolio;
import com.example.pi_ease.DAO.Entities.Project;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.Message.InvestmentDTO.Request;
import com.example.pi_ease.Message.InvestmentDTO.Response;
import com.example.pi_ease.Repositories.InvestmentRepository;
import com.example.pi_ease.Repositories.PortfolioRepository;
import com.example.pi_ease.Repositories.ProjectRepository;
import com.example.pi_ease.Repositories.UserRepository;
import com.example.pi_ease.Service.Interfaces.IInvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
@Service

public class InvestmentService implements IInvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    Clock clock;
    @Override
    public List<Response> getInvestments(long cin) {
        List<Investment> investments = investmentRepository.findInvestmentByUserInvestorCin(cin);
        List<Response> investmentResponses = new ArrayList<>();
        for (Investment investment : investments) {
            Response investmentResponse = new Response(investment);
            investmentResponses.add(investmentResponse);
        }
        return investmentResponses;    }

    @Override
    public Response createInvestment(int projectId, Request investmentDTO, long cin,int portfolioId) {
        Project project = projectRepository.findByIdP(projectId);

        if (!project.isOpenForInvestment()) {
            throw new RuntimeException("Project is not open for investment");
        }

        int remainingCapacity = project.getRemainingInvestmentCapacity();
        if (investmentDTO.getAmount() > remainingCapacity) {
            throw new RuntimeException("Investment amount exceeds remaining project capacity");
        }


        User user = userRepository.findByCin(cin);
        String investorEmail = user.getMail();

        Investment investment = investmentDTO.toEntity(user);

       Portfolio portfolio = portfolioRepository.findPortfolioByPort(portfolioId);

         investment.setPortfolio(portfolio);
         investment.setProject(project);


        investment = investmentRepository.save(investment);





        project.setCurrentInvestingAmount(project.getCurrentInvestingAmount() + investment.getAmount());
        project.setInvestorCount(project.getInvestorCount() + 1);
        projectRepository.save(project);

        String subject = "Investment Confirmation";
        String message = "Dear " + user.getFirst_name() + ",\n\n" +
                "Thank you for investing " + investment.getAmount() + " in the project " +
                project.getTitle() + ".\n\n" +
                "Best regards,\n" +
                "The Ease MicroFinance team";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(investorEmail);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);

        return new Response(investment);

        //return new Response(investment);
    }



    @Override
    public Investment add(Investment investment) {
        return investmentRepository.save(investment);    }

    @Override
    public Investment edit(Investment investment) {
        return investmentRepository.save(investment);    }

    @Override
    public List<Investment> selectAll() {
        return investmentRepository.findAll();

    }

    @Override
    public Investment selectByID(Long investmentId) {
        return investmentRepository.findById(investmentId).get();    }

    @Override
    public void deleteById(Long investmentId) {
        investmentRepository.deleteById(investmentId);

    }

    @Override
    public void delete(Investment investment) {
        investmentRepository.delete(investment);

    }

    @Override
    public List<Investment> addAll(List<Investment> listInvestment) {
        return investmentRepository.saveAll(listInvestment);
    }

    @Override
    public void deleteAll(List<Investment> listInvestment) {
        investmentRepository.deleteAll(listInvestment);

    }
}
