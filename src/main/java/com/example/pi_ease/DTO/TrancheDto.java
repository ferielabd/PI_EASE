package com.example.pi_ease.DTO;
import lombok.Data;


import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TrancheDto {
    private int idT;
    int nbT;
    BigDecimal monPay;
    LocalDate payDate;
}
