package com.projects.smartbankingapi.mapper.master;

import com.projects.smartbankingapi.dto.master.BnMAccountCardDetailDto;
import com.projects.smartbankingapi.model.master.BnMAccountCardDetail;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {BnMAccountMapper.class, BnMCardDetailMapper.class})
public interface BnMAccountCardDetailMapper {
    BnMAccountCardDetail toEntity(BnMAccountCardDetailDto bnMAccountCardDetailDto);

    BnMAccountCardDetailDto toDto(BnMAccountCardDetail bnMAccountCardDetail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnMAccountCardDetail partialUpdate(BnMAccountCardDetailDto bnMAccountCardDetailDto, @MappingTarget BnMAccountCardDetail bnMAccountCardDetail);
}