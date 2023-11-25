package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRStatusDto;
import com.projects.smartbankingapi.model.reference.BnRStatus;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRStatusMapper {
    BnRStatus toEntity(BnRStatusDto bnRStatusDto);

    BnRStatusDto toDto(BnRStatus bnRStatus);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRStatus partialUpdate(BnRStatusDto bnRStatusDto, @MappingTarget BnRStatus bnRStatus);
}