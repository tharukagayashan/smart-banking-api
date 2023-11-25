package com.projects.smartbankingapi.mapper.master;

import com.projects.smartbankingapi.dto.master.BnMLoanDto;
import com.projects.smartbankingapi.mapper.reference.BnRLoanProductMapper;
import com.projects.smartbankingapi.mapper.reference.BnRStatusMapper;
import com.projects.smartbankingapi.model.master.BnMLoan;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnRStatusMapper.class, BnMCustomerMapper.class, BnRLoanProductMapper.class})
public interface BnMLoanMapper {
    BnMLoan toEntity(BnMLoanDto bnMLoanDto);

    BnMLoanDto toDto(BnMLoan bnMLoan);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnMLoan partialUpdate(BnMLoanDto bnMLoanDto, @MappingTarget BnMLoan bnMLoan);
}