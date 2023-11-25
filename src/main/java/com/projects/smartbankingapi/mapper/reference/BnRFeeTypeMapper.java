package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRFeeTypeDto;
import com.projects.smartbankingapi.model.reference.BnRFeeType;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRFeeTypeMapper {
    BnRFeeType toEntity(BnRFeeTypeDto bnRFeeTypeDto);

    BnRFeeTypeDto toDto(BnRFeeType bnRFeeType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRFeeType partialUpdate(BnRFeeTypeDto bnRFeeTypeDto, @MappingTarget BnRFeeType bnRFeeType);
}