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

    @Mappings({
            @Mapping(target = "bnRAccountType.accountTypeId", source = "accountTypeId"),
            @Mapping(target = "bnRStatus.statusId", source = "statusId"),
            @Mapping(target = "bnRCurrency.currencyId", source = "currencyId"),
            @Mapping(target = "bnRBranch.branchId", source = "branchId"),
            @Mapping(target = "bnMCustomer.customerId", source = "customerId")
    })
    BnMAccount toEntity(BnMAccountDto bnMAccountDto);

    @Mappings({
            @Mapping(target = "accountTypeId", source = "bnRAccountType.accountTypeId"),
            @Mapping(target = "statusId", source = "bnRStatus.statusId"),
            @Mapping(target = "currencyId", source = "bnRCurrency.currencyId"),
            @Mapping(target = "branchId", source = "bnRBranch.branchId"),
            @Mapping(target = "customerId", source = "bnMCustomer.customerId")
    })
    BnMAccountDto toDto(BnMAccount bnMAccount);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnMAccount partialUpdate(BnMAccountDto bnMAccountDto, @MappingTarget BnMAccount bnMAccount);

    @Mappings({
            @Mapping(target = "accountTypeId", source = "bnRAccountType.accountTypeId"),
            @Mapping(target = "statusId", source = "bnRStatus.statusId"),
            @Mapping(target = "currencyId", source = "bnRCurrency.currencyId"),
            @Mapping(target = "branchId", source = "bnRBranch.branchId"),
            @Mapping(target = "customerId", source = "bnMCustomer.customerId")
    })
    List<BnMAccountDto> entityListToDtoList(List<BnMAccount> accounts);
}