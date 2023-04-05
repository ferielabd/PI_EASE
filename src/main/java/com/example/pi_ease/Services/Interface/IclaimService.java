package com.example.pi_ease.Services.Interface;

import com.example.pi_ease.DAO.Entities.Claim;
import com.example.pi_ease.DAO.Entities.TypeClaim;
import freemarker.template.TemplateException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IclaimService {
    Claim add(/*MultipartFile file,*/ Claim cl);
    Claim edit(Claim cl);
    void modifier(Integer id,boolean traite)throws TemplateException, MessagingException, IOException;
    void ajouter (MultipartFile file, String desc, TypeClaim type, int idt, int idc);
    List<Claim> selectAll();
    Claim selectById(Integer id);
    void deleteById(Integer id);
    void delete (Claim cl);
    List<Claim> addAll(List<Claim> List);
    void deleteAll(List<Claim> List);
    List<Claim> search(String q);
    List<Claim> search2(Date q);
    String getForbiddenWords();

}