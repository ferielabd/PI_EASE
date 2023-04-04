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

    public int getIdC() {
        return IdC;
    }

    public void setIdC(int idC) {
        IdC = idC;
    }

    public LocalDate getD_Date() {
        return D_Date;
    }

    public void setD_Date(LocalDate d_Date) {
        D_Date = d_Date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public LocalDate getOB_Date() {
        return OB_Date;
    }

    public void setOB_Date(LocalDate OB_Date) {
        this.OB_Date = OB_Date;
    }

    public float getTaux_interet() {
        return taux_interet;
    }

    public void setTaux_interet(float taux_interet) {
        this.taux_interet = taux_interet;
    }

    public BigDecimal getMontant_demander() {
        return montant_demander;
    }

    public void setMontant_demander(BigDecimal montant_demander) {
        this.montant_demander = montant_demander;
    }

    public BigDecimal getMonPP() {
        return monPP;
    }

    public void setMonPP(BigDecimal monPP) {
        this.monPP = monPP;
    }

    public BigDecimal getRestApay() {
        return RestApay;
    }

    public void setRestApay(BigDecimal restApay) {
        RestApay = restApay;
    }

    public CreditStatusType getCreditStatusType() {
        return creditStatusType;
    }

    public void setCreditStatusType(CreditStatusType creditStatusType) {
        this.creditStatusType = creditStatusType;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
