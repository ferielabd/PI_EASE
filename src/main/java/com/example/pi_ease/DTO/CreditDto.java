package com.example.pi_ease.DTO;

import com.example.pi_ease.DAO.Entities.CreditStatusType;
import com.example.pi_ease.DAO.Entities.TypeCredit;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;
@Data
public class CreditDto {

    int IdC;

    Date D_Date;

    float taux_interet;

    Date OB_Date;
    BigDecimal montant;

    BigDecimal monPP;

    BigDecimal RestApay;
    CreditStatusType creditStatusType;

    TypeCredit typeCredit;

    String attachment;


    String Description;

}
