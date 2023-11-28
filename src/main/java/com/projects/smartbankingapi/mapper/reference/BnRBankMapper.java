package com.projects.smartbankingapi.mapper.reference;

import com.projects.smartbankingapi.dto.reference.BnRBankDto;
import com.projects.smartbankingapi.model.reference.BnRBank;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BnRBankMapper {
    BnRBank toEntity(BnRBankDto bnRBankDto);

    BnRBankDto toDto(BnRBank bnRBank);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BnRBank partialUpdate(BnRBankDto bnRBankDto, @MappingTarget BnRBank bnRBank);

    List<BnRBankDto> entityListToDtoList(List<BnRBank> banks);
}