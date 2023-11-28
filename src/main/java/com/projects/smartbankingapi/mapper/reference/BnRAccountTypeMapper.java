package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRAccountTypeDto;
import com.projects.smartbankingapi.model.reference.BnRAccountType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRAccountTypeMapper {
    BnRAccountType toEntity(BnRAccountTypeDto bnRAccountTypeDto);

    BnRAccountTypeDto toDto(BnRAccountType bnRAccountType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRAccountType partialUpdate(BnRAccountTypeDto bnRAccountTypeDto, @MappingTarget BnRAccountType bnRAccountType);

    List<BnRAccountTypeDto> entityListToDtoList(List<BnRAccountType> accountTypes);
}