package com.projects.smartbankingapi.mapper.master;

import com.projects.smartbankingapi.dto.master.BnMCardDetailDto;
import com.projects.smartbankingapi.model.master.BnMCardDetail;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnMCardDetailMapper {
    BnMCardDetail toEntity(BnMCardDetailDto bnMCardDetailDto);

    BnMCardDetailDto toDto(BnMCardDetail bnMCardDetail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnMCardDetail partialUpdate(BnMCardDetailDto bnMCardDetailDto, @MappingTarget BnMCardDetail bnMCardDetail);
}