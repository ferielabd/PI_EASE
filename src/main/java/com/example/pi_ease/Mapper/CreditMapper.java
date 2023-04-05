package com.example.pi_ease.Mapper;

import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DTO.CrApplyCreditDto;
import com.example.pi_ease.DTO.CrPayCreditOffResponseDto;
import com.example.pi_ease.DTO.CreditDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditMapper {
    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    Credit convertToCredit(CrApplyCreditDto crApplyCreditDto);

    CreditDto convertToCreditDto(Credit credit);

    CrPayCreditOffResponseDto convertToCrPayCreditOffResponseDto(Credit credit);
}


