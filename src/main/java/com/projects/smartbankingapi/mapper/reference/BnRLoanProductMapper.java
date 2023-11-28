package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRLoanProductDto;
import com.projects.smartbankingapi.model.reference.BnRLoanProduct;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnRLoanTypeMapper.class, BnRIntRateMapper.class, BnRLoanPeriodMapper.class})
public interface BnRLoanProductMapper {

    @Mappings({
            @Mapping(target = "bnRLoanType.loanTypeId", source = "loanTypeId"),
            @Mapping(target = "bnRIntRate.intRateId", source = "intRateId"),
            @Mapping(target = "bnRLoanPeriod.periodId", source = "periodId")
    })
    BnRLoanProduct toEntity(BnRLoanProductDto bnRLoanProductDto);

    @Mappings({
            @Mapping(target = "loanTypeId", source = "bnRLoanType.loanTypeId"),
            @Mapping(target = "intRateId", source = "bnRIntRate.intRateId"),
            @Mapping(target = "periodId", source = "bnRLoanPeriod.periodId")
    })
    BnRLoanProductDto toDto(BnRLoanProduct bnRLoanProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRLoanProduct partialUpdate(BnRLoanProductDto bnRLoanProductDto, @MappingTarget BnRLoanProduct bnRLoanProduct);

    @Mappings({
            @Mapping(target = "loanTypeId", source = "bnRLoanType.loanTypeId"),
            @Mapping(target = "intRateId", source = "bnRIntRate.intRateId"),
            @Mapping(target = "periodId", source = "bnRLoanPeriod.periodId")
    })
    List<BnRLoanProductDto> entityListToDtoList(List<BnRLoanProduct> loanProducts);
}