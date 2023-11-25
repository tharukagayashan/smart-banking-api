package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRLoanTypeDto;
import com.projects.smartbankingapi.model.reference.BnRLoanType;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRLoanTypeMapper {
    BnRLoanType toEntity(BnRLoanTypeDto bnRLoanTypeDto);

    BnRLoanTypeDto toDto(BnRLoanType bnRLoanType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRLoanType partialUpdate(BnRLoanTypeDto bnRLoanTypeDto, @MappingTarget BnRLoanType bnRLoanType);
}