package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRLoanProductDto;
import com.projects.smartbankingapi.model.reference.BnRLoanProduct;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnRLoanTypeMapper.class, BnRIntRateMapper.class, BnRLoanPeriodMapper.class})
public interface BnRLoanProductMapper {
    BnRLoanProduct toEntity(BnRLoanProductDto bnRLoanProductDto);

    BnRLoanProductDto toDto(BnRLoanProduct bnRLoanProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRLoanProduct partialUpdate(BnRLoanProductDto bnRLoanProductDto, @MappingTarget BnRLoanProduct bnRLoanProduct);
}