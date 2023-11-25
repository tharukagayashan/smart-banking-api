package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRIntRateDto;
import com.projects.smartbankingapi.model.reference.BnRIntRate;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRIntRateMapper {
    BnRIntRate toEntity(BnRIntRateDto bnRIntRateDto);

    BnRIntRateDto toDto(BnRIntRate bnRIntRate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRIntRate partialUpdate(BnRIntRateDto bnRIntRateDto, @MappingTarget BnRIntRate bnRIntRate);
}