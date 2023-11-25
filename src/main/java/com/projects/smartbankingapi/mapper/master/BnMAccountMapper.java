package com.projects.smartbankingapi.mapper.master;

import com.projects.smartbankingapi.dto.master.BnMAccountDto;
import com.projects.smartbankingapi.mapper.reference.BnRAccountTypeMapper;
import com.projects.smartbankingapi.mapper.reference.BnRBranchMapper;
import com.projects.smartbankingapi.mapper.reference.BnRCurrencyMapper;
import com.projects.smartbankingapi.mapper.reference.BnRStatusMapper;
import com.projects.smartbankingapi.model.master.BnMAccount;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnMCustomerMapper.class, BnRAccountTypeMapper.class, BnRStatusMapper.class, BnRCurrencyMapper.class, BnRBranchMapper.class})
public interface BnMAccountMapper {
    BnMAccount toEntity(BnMAccountDto bnMAccountDto);

    BnMAccountDto toDto(BnMAccount bnMAccount);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnMAccount partialUpdate(BnMAccountDto bnMAccountDto, @MappingTarget BnMAccount bnMAccount);

    List<BnMAccountDto> entityListToDtoList(List<BnMAccount> accounts);
}