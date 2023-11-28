package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRLoanPeriodDto;
import com.projects.smartbankingapi.model.reference.BnRLoanPeriod;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRLoanPeriodMapper {
    BnRLoanPeriod toEntity(BnRLoanPeriodDto bnRLoanPeriodDto);

    BnRLoanPeriodDto toDto(BnRLoanPeriod bnRLoanPeriod);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRLoanPeriod partialUpdate(BnRLoanPeriodDto bnRLoanPeriodDto, @MappingTarget BnRLoanPeriod bnRLoanPeriod);

    List<BnRLoanPeriodDto> entityListToDtoList(List<BnRLoanPeriod> loanPeriods);
}