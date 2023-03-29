package com.example.pi_ease.DTO;

import com.example.pi_ease.DAO.Entities.CreditStatusType;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreditDto {

    int IdC;

    LocalDate D_Date;
    LocalDate due_date;
    LocalDate OB_Date;
    float taux_interet;
    BigDecimal montant_demander;

    BigDecimal monPP;

    BigDecimal RestApay;
    CreditStatusType creditStatusType;
    String attachment;


    String Description;

}
