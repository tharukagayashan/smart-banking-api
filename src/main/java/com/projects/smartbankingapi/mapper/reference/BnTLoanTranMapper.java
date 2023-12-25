package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.transaction.BnTLoanTranDto;
import com.projects.smartbankingapi.model.transaction.BnTLoanTran;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BnTLoanTranMapper {

    BnTLoanTran toEntity(BnTLoanTranDto bnTLoanTranDto);

    BnTLoanTranDto toDto(BnTLoanTran bnTLoanTran);

    List<BnTLoanTranDto> entityListToDtoList(List<BnTLoanTran> bnTLoanTrans);

}