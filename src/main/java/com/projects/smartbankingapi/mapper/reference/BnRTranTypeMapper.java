package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRTranTypeDto;
import com.projects.smartbankingapi.model.reference.BnRTranType;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRTranTypeMapper {
    BnRTranType toEntity(BnRTranTypeDto bnRTranTypeDto);

    BnRTranTypeDto toDto(BnRTranType bnRTranType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRTranType partialUpdate(BnRTranTypeDto bnRTranTypeDto, @MappingTarget BnRTranType bnRTranType);
}