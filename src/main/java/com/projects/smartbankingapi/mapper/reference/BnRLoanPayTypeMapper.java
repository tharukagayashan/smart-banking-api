package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRLoanPayTypeDto;
import com.projects.smartbankingapi.model.reference.BnRLoanPayType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BnRLoanPayTypeMapper {

    BnRLoanPayType toEntity(BnRLoanPayTypeDto bnRLoanPayTypeDto);

    BnRLoanPayTypeDto toDto(BnRLoanPayType bnRLoanPayType);

    List<BnRLoanPayTypeDto> entityListToDtoList(List<BnRLoanPayType> bnRLoanPayTypes);

}