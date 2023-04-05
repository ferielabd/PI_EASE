package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.*;
import com.example.pi_ease.DAO.Repositories.ClaimRepo;
import com.example.pi_ease.DAO.Repositories.CreditRepo;
import com.example.pi_ease.DAO.Repositories.TransactionRepo;
import com.example.pi_ease.DAO.Repositories.UserRepo;
import com.example.pi_ease.Services.Interface.IclaimService;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
@AllArgsConstructor
public class ClaimService implements IclaimService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private CreditRepo creditRepo;
    private ClaimRepo claimRepo;
    private EmailService emailService;
    @Autowired
    private com.example.pi_ease.Services.Interface.EmailService emailServiceM;
    @Override
    public Claim add(Claim cl) {

        return claimRepo.save(cl);
    }

    @Override
    public Claim edit(Claim cl) {
        return claimRepo.save(cl);
    }

    @Override
    public void modifier(Integer id, boolean traite)throws TemplateException, MessagingException, IOException {
        Claim cl =claimRepo.findById(id).get();
        cl.setTraiteClaim(traite);
        Calendar cal = Calendar.getInstance();
        Date datetraite=cal.getTime();
        cl.setDateTraite(datetraite);
        User user =cl.getUserc();
        claimRepo.save(cl);
        // this.emailService.sendSimpleEmail(user, "Status of your claim", "your complaint is processed");
        emailServiceM.sendEmailRegister(user);
    }
    @Override
    public String getForbiddenWords() {
        String content = "";
        try {

            File file = new File("C:\\Users\\ASUS\\OneDrive\\Bureau\\badwords.txt");
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content.trim();

    }

    @Override
    public void ajouter(MultipartFile file, String desc, TypeClaim type, int idt , long idc) {
        Claim cl =new Claim();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            cl.setAttachClaim(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean traite=false;
        /*cl.setAttachClaim(attach);*/
        cl.setType(type);
        cl.setDescClaim(desc);
        cl.setTraiteClaim(traite);
        Calendar cal = Calendar.getInstance();
        Date dateclaim=cal.getTime();
        cl.setDateClaim(dateclaim);

        User user = userRepository.findById(1L).get();
        cl.setUserc(user);
        claimRepo.save(cl);
        cl.setRefclaim(String.valueOf(cl.getIdClaim())+"."+cl.getDateClaim());
        if ((idt != 0 )&& (idc == 0)){
            Transaction tr =transactionRepo.findById(idt).get();
            cl.setRefTR("la transaction numero"+tr.getIdTraction());
            claimRepo.save(cl);
        } else if ((idt == 0) && (idc!=0)){
            Credit cr =creditRepo.findByIdAndUserCredit(idc,user);
            cl.setRefCR("le credit numero"+cr.getId());
            claimRepo.save(cl);
        }
        //List<String> badWords = Arrays.asList("aaa", "badword2", "badword3");
        ArrayList<String> badwords = new ArrayList<>(Arrays.asList(getForbiddenWords().split("/")));

        System.out.println(badwords);
        for (String word : badwords) {
            if (cl.getDescClaim().toLowerCase().contains((CharSequence) word)) {

                String content = cl.getDescClaim().toLowerCase().replace(word, "***");
                cl.setDescClaim(content);
                //User user = authController.getCurrentUser();

                int alertCount = user.getCount();
                user.setCount(alertCount + 1);
                System.out.println(alertCount);
                userRepository.save(user);
                claimRepo.save(cl);
                if (alertCount >= 3) {

                    user.setActive(0);
                    userRepository.save(user);
                    this.emailService.sendSimpleEmail(user.getEmail(),"Account blocked", "your account is now blocked due to your bad behaviour ");
                }

            }

            claimRepo.save(cl);

            //If the user has been alerted three times, ban them for three days



        }

    }


    @Override
    public List<Claim> selectAll() {
        return  claimRepo.findAll();
    }

    @Override
    public Claim selectById(Integer id) {
        return claimRepo.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        claimRepo.deleteById(id);
    }

    @Override
    public void delete(Claim cl) {
        claimRepo.delete(cl);
    }

    @Override
    public List<Claim> addAll(List<Claim> List) {
        return claimRepo.saveAll(List);
    }

    @Override
    public void deleteAll(List<Claim> List) {
        claimRepo.deleteAll(List);
    }

    @Override
    public List<Claim> search(String q) {
        return  claimRepo.findByRefclaim (q);
    }

    @Override
    public List<Claim> search2(Date q) {
        return  claimRepo.findByDateClaim(q);
    }




}

