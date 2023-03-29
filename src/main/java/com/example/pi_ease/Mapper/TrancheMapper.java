package com.example.pi_ease.Mapper;

import com.example.pi_ease.DAO.Entities.Credit;
import com.example.pi_ease.DAO.Entities.Tranche;
import com.example.pi_ease.DTO.CrApplyCreditDto;
import com.example.pi_ease.DTO.CreditDto;
import com.example.pi_ease.DTO.TrancheDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface TrancheMapper {

    TrancheMapper INSTANCE = Mappers.getMapper(TrancheMapper.class);

    Tranche convertToTranche(TrancheDto TrancheDto);

    TrancheDto convertToTrancheDto(Tranche tranche);

}
