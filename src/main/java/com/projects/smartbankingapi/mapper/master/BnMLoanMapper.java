package com.projects.smartbankingapi.mapper.master;

import com.projects.smartbankingapi.dto.master.BnMLoanDto;
import com.projects.smartbankingapi.mapper.reference.BnRLoanProductMapper;
import com.projects.smartbankingapi.mapper.reference.BnRStatusMapper;
import com.projects.smartbankingapi.model.master.BnMLoan;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnRStatusMapper.class, BnMCustomerMapper.class, BnRLoanProductMapper.class})
public interface BnMLoanMapper {

    @Mappings({
            @Mapping(target = "bnMAccount.accountId", source = "accountId"),
            @Mapping(target = "bnRStatus.statusId", source = "statusId"),
            @Mapping(target = "bnRLoanProduct.productId", source = "productId")
    })
    BnMLoan toEntity(BnMLoanDto bnMLoanDto);

    @Mappings({
            @Mapping(target = "accountId", source = "bnMAccount.accountId"),
            @Mapping(target = "statusId", source = "bnRStatus.statusId"),
            @Mapping(target = "productId", source = "bnRLoanProduct.productId")
    })
    BnMLoanDto toDto(BnMLoan bnMLoan);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnMLoan partialUpdate(BnMLoanDto bnMLoanDto, @MappingTarget BnMLoan bnMLoan);

    @Mappings({
            @Mapping(target = "bnMAccount.accountId", source = "accountId"),
            @Mapping(target = "bnRStatus.statusId", source = "statusId"),
            @Mapping(target = "bnRLoanProduct.productId", source = "productId")
    })
    List<BnMLoanDto> entityListToDtoList(List<BnMLoan> content);
}